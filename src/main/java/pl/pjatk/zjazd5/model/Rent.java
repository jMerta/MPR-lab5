package pl.pjatk.zjazd5.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Rent {
    private int id;
    private Client client;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
}
