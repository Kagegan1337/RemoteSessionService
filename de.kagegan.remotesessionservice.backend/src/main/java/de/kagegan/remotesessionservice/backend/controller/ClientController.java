package de.kagegan.remotesessionservice.backend.controller;

import de.kagegan.remotesessionservice.backend.repository.AccessInformationRepository;
import de.kagegan.remotesessionservice.backend.repository.ClientRepository;
import de.kagegan.remotesessionservice.entities.accessinformation.AccessInformation;
import de.kagegan.remotesessionservice.entities.client.Client;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepository;
    private final AccessInformationRepository accessInformationRepository;

    public ClientController(ClientRepository clientRepository, AccessInformationRepository accessInformationRepository) {
        this.clientRepository = clientRepository;
        this.accessInformationRepository = accessInformationRepository;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createOrUpdateClient(@RequestBody Client client) {
        clientRepository.save (client);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{clientId}/accessInformation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrUpdateAccessInformation(@RequestBody AccessInformation accessInformation, @PathVariable String clientId) {
        Client c = clientRepository.findClientByClientName(clientId);
        if( c == null)
            return ResponseEntity.notFound().build();
        accessInformation.setClient(c);
        accessInformationRepository.save(accessInformation);
        c.getAccessInformation().add(accessInformation);
        clientRepository.save(c);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/import", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> importClients(@RequestBody Client[] clients) {
        clientRepository.saveAll(Arrays.asList(clients));
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{clientName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClientById(@PathVariable String clientName) {
        Client c = clientRepository.findClientByClientName(clientName);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{clientName}/delete")
    public ResponseEntity<Client> deleteClient(@PathVariable String clientName) {
        Client c = clientRepository.findClientByClientName(clientName);
        if( c != null) {
            clientRepository.delete(c);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Client>> getAll() {
        return ResponseEntity.ok(clientRepository.findAll());
    }
}
