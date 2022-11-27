package pl.pjatk.zjazd5.repository;

import org.springframework.stereotype.Repository;
import pl.pjatk.zjazd5.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {
    private List<Client> clientList = new ArrayList<>();

    public void addClient(Client client) throws Exception {
        if (clientList.contains(client)) {
            throw new Exception();
        }
        clientList.add(client);
    }

    public Optional<Client> findClientById(int id) {
        return clientList.stream().filter(it -> it.getId() == id).findFirst();
    }

    public void removeAll() {
        clientList = new ArrayList<>();
    }
}