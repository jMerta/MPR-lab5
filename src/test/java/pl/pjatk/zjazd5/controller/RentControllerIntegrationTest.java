package pl.pjatk.zjazd5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.pjatk.zjazd5.model.Car;
import pl.pjatk.zjazd5.model.CarClass;
import pl.pjatk.zjazd5.model.Rent;
import pl.pjatk.zjazd5.model.RentRequest;
import pl.pjatk.zjazd5.repository.CarRepository;
import pl.pjatk.zjazd5.repository.RentRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class RentControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void cleanup(){
        carRepository.removeAll();
        rentRepository.removeALl();
    }

    @Test
    void createNewRent() throws Exception {
        Car car = new Car(0, "make", "model", "vin", CarClass.STANDARD, false, 50);
        carRepository.save(car);
        RentRequest rentRequest = new RentRequest(0, 0, LocalDate.now(), LocalDate.now().plusDays(5));

        String rentRequestJson = objectMapper.writeValueAsString(rentRequest);

        Rent result = webTestClient.post().uri("/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(rentRequestJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Rent.class)
                .returnResult().getResponseBody();

        assertNotNull(result);
        assertEquals(result.getPrice(), 250);

        assertTrue(rentRepository.findAll().contains(result));
    }
}