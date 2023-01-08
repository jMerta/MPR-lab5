package pl.pjatk.zjazd5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentRequest {
    private Integer clientId;
    private int carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
