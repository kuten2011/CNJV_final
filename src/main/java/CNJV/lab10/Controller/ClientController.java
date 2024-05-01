package CNJV.lab10.Controller;

import CNJV.lab10.Repository.ClientRepository;
import CNJV.lab10.Service.ClientService;
import CNJV.lab10.Service.UserService;
import CNJV.lab10.dto.ClientDTO;
import CNJV.lab10.model.Client;
import CNJV.lab10.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    UserService userService;

    @GetMapping({"", "/"})
    public Iterable<Client> getAllClient() {
        return clientService.getAllClient();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) throws Exception {
        Client client = clientService.getClient(id);
        if (clientDTO.getName() != null) client.setName(clientDTO.getName());
        if (clientDTO.getPhone() != null) client.setPhone(clientDTO.getPhone());
        if (clientDTO.getEmail() != null) client.setEmail(clientDTO.getEmail());
        clientService.saveClient(client);

        User user = userService.loadUserByUsername(clientService.getClient(id).getUser().getUsername());
        if (clientDTO.getName() != null) user.setName(clientDTO.getName());
        if (clientDTO.getPhone() != null) user.setPhone(clientDTO.getPhone());
        if (clientDTO.getEmail() != null) user.setEmail(clientDTO.getEmail());
        userService.update(user);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
