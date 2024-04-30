package CNJV.lab10.Service;

import CNJV.lab10.Repository.ClientRepository;
import CNJV.lab10.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Iterable<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(Long id) throws Exception {
        return clientRepository.findById(id).orElseThrow(() -> new Exception("Client not found"));
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void removeClient(Long id) {
        clientRepository.deleteById(id);
    }
}
