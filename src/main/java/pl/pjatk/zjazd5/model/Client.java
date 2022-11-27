package pl.pjatk.zjazd5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private final int id;
    private final String name;
    private final String surname;
    private final String city;
    private Car rentedCar;
}