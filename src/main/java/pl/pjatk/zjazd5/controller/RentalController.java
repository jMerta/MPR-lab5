package pl.pjatk.zjazd5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.zjazd5.model.Car;
import pl.pjatk.zjazd5.service.RentalService;

@RestController
@RequestMapping("/rent")
public class RentalController {
    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/save")
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        rentalService.addNewCar(car);

        return ResponseEntity.ok(car);
    }

}
