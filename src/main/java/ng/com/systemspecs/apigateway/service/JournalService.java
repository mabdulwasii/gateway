package ng.com.systemspecs.apigateway.service;

import java.util.List;
import java.util.Optional;

import ng.com.systemspecs.apigateway.domain.Journal;

public interface JournalService {
	Journal save(Journal journal);
    

    /**
     * Get all the addresses.
     *
     * @return the list of entities.
     */
    List<Journal> findAll();


    /**
     * Get the "id" address.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Journal> findOne(Long id);

    /**
     * Delete the "id" address.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
