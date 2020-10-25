package de.kagegan.remotesessionservice.backend.repository;

import de.kagegan.remotesessionservice.entities.accessinformation.AccessInformation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessInformationRepository extends PagingAndSortingRepository<AccessInformation, Long> {
}
