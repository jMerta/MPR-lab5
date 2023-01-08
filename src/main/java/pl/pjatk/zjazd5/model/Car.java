package pl.pjatk.zjazd5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@AllArgsConstructor
@With
public class Car {
    private final int id;
    private final String make;
    private final String model;
    private final String vin;
    private final CarClass carClass;
    private boolean isRented;
    private final int dailyRate;
}
