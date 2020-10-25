package de.kagegan.remotesessionservice.backend.repository;

import de.kagegan.remotesessionservice.entities.client.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    @Query("SELECT c from Client c where c.clientName = ?1")
    Client findClientByClientName(String clientName);
}
