package de.kagegan.remotesessionservice.backend.controller;

import de.kagegan.remotesessionservice.backend.repository.AccessInformationRepository;
import de.kagegan.remotesessionservice.backend.repository.ClientRepository;
import de.kagegan.remotesessionservice.entities.accessinformation.AccessInformation;
import de.kagegan.remotesessionservice.entities.client.Client;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accessInformation")
public class AccessInformationController {

    private final AccessInformationRepository accessInformationRepository;
    private final ClientRepository clientRepository;

    public AccessInformationController(AccessInformationRepository accessInformationRepository, ClientRepository clientRepository) {
        this.accessInformationRepository = accessInformationRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping(value = "/save/{clientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrUpdateAccessInformationForClient(@RequestBody AccessInformation accessInformation,
                                                                         @PathVariable String clientId) {
        Client c = clientRepository.findClientByClientName(clientId);
        if(c == null) {
            return ResponseEntity.notFound().build();
        }
        accessInformation.setClient(c);
        accessInformationRepository.save(accessInformation);
        clientRepository.save(c);
        return ResponseEntity.noContent().build();
    }

}
