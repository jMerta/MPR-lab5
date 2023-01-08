package pl.pjatk.zjazd5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.zjazd5.exception.ValidationException;
import pl.pjatk.zjazd5.model.Car;
import pl.pjatk.zjazd5.service.CarService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/create")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            carService.create(car);
        } catch (ValidationException validationException) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(car);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCarList(@RequestParam(required = false, name = "isRented") Boolean isRented) {
        List<Car> carList = carService.findAllWithFilters(Optional.ofNullable(isRented));

        return ResponseEntity.ok(carList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable int id) {
        Optional<Car> car = carService.findById(id);

        return car.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity deleteAll() {
        carService.deleteAll();

        return ResponseEntity.ok().build();
    }
}
