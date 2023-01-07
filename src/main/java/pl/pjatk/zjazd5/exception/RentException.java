package pl.pjatk.zjazd5.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RentException extends RuntimeException {
    private final String messsage;
}