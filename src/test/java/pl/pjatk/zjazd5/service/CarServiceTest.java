package pl.pjatk.zjazd5.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.pjatk.zjazd5.exception.DatabaseException;
import pl.pjatk.zjazd5.exception.ValidationException;
import pl.pjatk.zjazd5.model.Car;
import pl.pjatk.zjazd5.model.CarClass;
import pl.pjatk.zjazd5.repository.CarRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarServiceTest {
    private CarRepository carRepository = mock(CarRepository.class);

    private CarService carService = new CarService(carRepository);


    @Test
    void shouldCreateNewCar() throws Exception {
        Car car = new Car(0, "make", "model", "vin", CarClass.STANDARD,false, 50);

        assertDoesNotThrow( ()-> carService.create(car));

        when(carRepository.findById(0)).thenReturn(Optional.of(car));


        Optional<Car> foundCar = carRepository.findById(0);

        assertTrue(foundCar.isPresent());
        assertEquals(foundCar.get(), car);
    }

    @Test
    void shouldThrowDatabaseException() throws Exception {
        Car car = new Car(0, "make", "model", "vin", CarClass.STANDARD,false, 50);

        doThrow(new Exception()).when(carRepository).save(any());

        assertThrows(DatabaseException.class, ()-> carService.create(car));
    }

    @Test
    void shouldThrowValidationExceptionWhenVinIsEmpty() {
        Car car = new Car(0, "make", "model", "", CarClass.STANDARD,false, 50);

        ValidationException validationException = assertThrows(ValidationException.class, () -> carService.create(car));

        assertEquals(validationException.getMessage(), "VIN is required!");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCar")
    void shouldThrowValidationException(Car car, String message) {
        ValidationException validationException = assertThrows(ValidationException.class, () -> carService.create(car));

        assertEquals(validationException.getMessage(), message);
    }



    private static Stream<Arguments> provideInvalidCar() {
        return Stream.of(
                Arguments.of(new Car(0, "make", "model", "", CarClass.STANDARD,false, 50),"VIN is required!" ),
                Arguments.of(new Car(0, "make", "model", null, CarClass.STANDARD,false, 50), "VIN is required!"),
                Arguments.of(new Car(0, "", "model", "vin", CarClass.STANDARD,false, 50),"Make is required!"),
                Arguments.of(new Car(0, null, "model", "vin", CarClass.STANDARD,false, 50), "Make is required!")
        );
    }
}