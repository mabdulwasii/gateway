package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.service.dto.WalletAccountTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.WalletAccountType}.
 */
public interface WalletAccountTypeService {

    /**
     * Save a walletAccountType.
     *
     * @param walletAccountTypeDTO the entity to save.
     * @return the persisted entity.
     */
    WalletAccountTypeDTO save(WalletAccountTypeDTO walletAccountTypeDTO);

    /**
     * Get all the walletAccountTypes.
     *
     * @return the list of entities.
     */
    List<WalletAccountTypeDTO> findAll();


    /**
     * Get the "id" walletAccountType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WalletAccountTypeDTO> findOne(Long id);

    /**
     * Delete the "id" walletAccountType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
