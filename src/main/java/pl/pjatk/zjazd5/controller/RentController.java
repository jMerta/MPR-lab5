package pl.pjatk.zjazd5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.zjazd5.model.Rent;
import pl.pjatk.zjazd5.model.RentRequest;
import pl.pjatk.zjazd5.service.RentalService;

@RestController
@RequestMapping("/rent")
public class RentController {

    private final RentalService rentalService;

    public RentController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/new")
    public ResponseEntity<Rent> addNewRent(@RequestBody RentRequest rentRequest) {
        System.out.println(rentRequest);

        return ResponseEntity.ok().build();
    }
}
