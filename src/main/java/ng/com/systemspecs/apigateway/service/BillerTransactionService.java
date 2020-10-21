package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.service.dto.BillerTransactionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.BillerTransaction}.
 */
public interface BillerTransactionService {

    /**
     * Save a billerTransaction.
     *
     * @param billerTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    BillerTransactionDTO save(BillerTransactionDTO billerTransactionDTO);

    /**
     * Get all the billerTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillerTransactionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" billerTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillerTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" billerTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
