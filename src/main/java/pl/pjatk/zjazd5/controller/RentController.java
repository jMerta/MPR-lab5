package pl.pjatk.zjazd5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.zjazd5.exception.RentException;
import pl.pjatk.zjazd5.exception.ValidationException;
import pl.pjatk.zjazd5.model.Rent;
import pl.pjatk.zjazd5.model.RentRequest;
import pl.pjatk.zjazd5.service.RentalService;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    private final RentalService rentalService;

    public RentController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<Rent> addNewRent(@RequestBody RentRequest rentRequest) {
        try {
            Rent rent = rentalService.rentCar(rentRequest);

            return ResponseEntity.ok(rent);
        } catch (ValidationException validationException) {
            return ResponseEntity.badRequest().build();
        } catch (RentException rentException) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Rent>> getRents() {
        List<Rent> rentList = rentalService.findALl();

        return ResponseEntity.ok(rentList);
    }

}
