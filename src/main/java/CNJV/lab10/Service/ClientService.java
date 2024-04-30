package CNJV.lab10.Service;

import CNJV.lab10.model.Client;

public interface ClientService {
    Iterable<Client> getAllClient();
    Client getClient(Long id) throws Exception;
    Client saveClient(Client client);
    void removeClient(Long id);
}