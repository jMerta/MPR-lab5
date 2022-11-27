package pl.pjatk.zjazd5.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentRequest {
    private int clientId;
    private int carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
