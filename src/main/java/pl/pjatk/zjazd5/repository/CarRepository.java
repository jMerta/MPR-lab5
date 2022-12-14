package pl.pjatk.zjazd5.repository;

import org.springframework.stereotype.Repository;
import pl.pjatk.zjazd5.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository {
    private List<Car> carList = new ArrayList<>();

    public void save(Car car) throws Exception {
        if (carList.contains(car)) {
            throw new Exception();
        }
        carList.add(car);
    }

    public Optional<Car> findById(int id) {
        return carList.stream().filter(it -> it.getId() == id).findFirst();
    }

    public void removeAll() {
        carList = new ArrayList<>();
    }

    public List<Car> findAll() {
        return carList;
    }

    public void setRentalStatus(Car car, boolean isRented) {
        carList.stream()
                .filter(it -> it.equals(car))
                .forEach(it -> it.setRented(isRented));
    }

    public List<Car> findAllByIsRented(boolean isRented) {
        return carList.stream().filter(it -> it.isRented() == isRented).toList();
    }
}
