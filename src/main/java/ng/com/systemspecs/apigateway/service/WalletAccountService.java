package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.service.dto.FundDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PostResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.SendMoneyDTO;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.WalletAccount}.
 */
public interface WalletAccountService {

    /**
     * Save a walletAccount.
     *
     * @param walletAccountDTO the entity to save.
     * @return the persisted entity.
     */
    WalletAccountDTO save(WalletAccountDTO walletAccountDTO);

    /**
     * Get all the walletAccounts.
     *
     * @return the list of entities.
     */
    List<WalletAccountDTO> findAll();
    List<WalletAccountDTO> findByUserIsCurrentUser();

    /**
     * Get the "id" walletAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WalletAccountDTO> findOne(Long id);

    /**
     * Delete the "id" walletAccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    PaymentResponseDTO fund(Profile  profile,  FundDTO fundDTO);
    PaymentResponseDTO sendMoney(Profile  profile, SendMoneyDTO sendMoneyDTO);
    
}
