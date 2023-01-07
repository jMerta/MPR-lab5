package pl.pjatk.zjazd5.service;

import org.springframework.stereotype.Service;
import pl.pjatk.zjazd5.exception.DatabaseException;
import pl.pjatk.zjazd5.exception.ValidationException;
import pl.pjatk.zjazd5.model.Car;
import pl.pjatk.zjazd5.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void create(Car car) {
        if (isInvalid(car.getVin())) {
            throw new ValidationException("VIN is required!");
        }
        if (isInvalid(car.getMake())) {
            throw new ValidationException("Make is required!");
        }

        try {
            carRepository.save(car);
        } catch (Exception e) {
            throw new DatabaseException("Database error: ", e);
        }
    }


    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    private boolean isInvalid(String attribute) {
        return attribute == null || attribute.isBlank();
    }

    public List<Car> findAllWithFilters(Optional<Boolean> isRented) {
        return isRented.map(carRepository::findAllByIsRented)
                .orElse(carRepository.findAll());
    }

    public void deleteAll() {
        carRepository.removeAll();
    }

    public void changeRentStatus(Car car, boolean isRented) {
        carRepository.setRentalStatus(car, isRented);
    }
}
