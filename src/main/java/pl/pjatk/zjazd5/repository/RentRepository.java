package pl.pjatk.zjazd5.repository;

import org.springframework.stereotype.Repository;
import pl.pjatk.zjazd5.model.Rent;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RentRepository {
    private List<Rent> rentList = new ArrayList<>();

    public Rent save(Rent rent) {
        rentList.add(rent);

        return rent;
    }

    public List<Rent> findAll() {
        return rentList;
    }

    public void removeALl() {
        rentList = new ArrayList<>();
    }
}
