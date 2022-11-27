package pl.pjatk.zjazd5.model;

import lombok.Data;

@Data
public class Rent {
    private int id;
    private Client client;
    private Car car;
    private int rentTimeInDays;
}
