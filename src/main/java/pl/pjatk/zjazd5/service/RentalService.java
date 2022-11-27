package pl.pjatk.zjazd5.service;

import org.springframework.stereotype.Service;
import pl.pjatk.zjazd5.exception.DatabaseException;
import pl.pjatk.zjazd5.exception.ValidationException;
import pl.pjatk.zjazd5.model.Car;
import pl.pjatk.zjazd5.repository.CarRepository;

import java.util.Optional;

@Service
public class RentalService {


    private final CarRepository carRepository;

    public RentalService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public void rentCar() {
        //TODO
    }

    public void addNewCar(Car car) {
        if (isInvalid(car.getVin())) {
            throw new ValidationException("VIN is required!");
        }
        if (isInvalid(car.getMake())) {
            throw new ValidationException("Make is required!");
        }

        try {
            carRepository.addCar(car);
        } catch (Exception e) {
            throw new DatabaseException("Database error: ", e);
        }
    }

    public Optional<Car> findCarById(int id) {
        return carRepository.findCarById(id);
    }

    private boolean isInvalid(String attribute) {
        return attribute == null || attribute.isBlank();
    }
}
