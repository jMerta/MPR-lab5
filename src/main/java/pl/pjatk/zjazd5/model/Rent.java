package pl.pjatk.zjazd5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Rent {
    private UUID id;
    private Client client;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private long price;
}
