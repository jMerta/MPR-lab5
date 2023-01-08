package pl.pjatk.zjazd5.integration;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class RentControllerIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    ObjectMapper objectMapper;


    @AfterEach
    void cleanup() {
        carRepository.removeAll();
        rentRepository.removeALl();
    }

    @Test
    void createNewRent() throws Exception {
        var rentRequest = new RentRequest(null, 0, LocalDate.now(), LocalDate.now().plusDays(5));
        var car = new Car(0, "skoda", "octavia", "vin", CarClass.STANDARD, false, 50);

        carRepository.save(car);

        var result = webTestClient.post().uri("/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(rentRequest))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Rent.class)
                .returnResult().getResponseBody();


        assertEquals(car.withRented(true), result.getCar());
        assertEquals(250, result.getPrice());
    }
}
