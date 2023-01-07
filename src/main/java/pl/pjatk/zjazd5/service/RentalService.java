package pl.pjatk.zjazd5.service;

import org.springframework.stereotype.Service;
import pl.pjatk.zjazd5.exception.RentException;
import pl.pjatk.zjazd5.model.Car;
import pl.pjatk.zjazd5.model.Rent;
import pl.pjatk.zjazd5.model.RentRequest;
import pl.pjatk.zjazd5.repository.RentRepository;

import java.util.List;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RentalService {

    private final CarService carService;
    private final RentRepository rentRepository;

    public RentalService(CarService carService, RentRepository rentRepository) {
        this.carService = carService;
        this.rentRepository = rentRepository;
    }

    public Rent rentCar(RentRequest rentRequest) {
        Car car = carService.findById(rentRequest.getCarId())
                .orElseThrow(() -> new RentException("Car does not exist"));

        if (car.isRented()) {
            throw new RentException("Car is currently rented. Please look for another one.");
        } else {
            long totalPrice = car.getDailyRate() * DAYS.between(rentRequest.getStartDate(), rentRequest.getEndDate());

            Rent rent = new Rent(UUID.randomUUID(), null, car, rentRequest.getStartDate(), rentRequest.getEndDate(), totalPrice);

            rentRepository.save(rent);
            carService.changeRentStatus(car, true);

            return rent;
        }
    }

    public List<Rent> findALl() {
        List<Rent> rentList = rentRepository.findAll();

        return rentList;
    }

}
