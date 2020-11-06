package ng.com.systemspecs.apigateway.service;

import java.util.List;
import java.util.Optional;

import ng.com.systemspecs.apigateway.domain.Journal;
import ng.com.systemspecs.apigateway.domain.JournalLine;
import ng.com.systemspecs.apigateway.domain.WalletAccount;

public interface JournalLineService {
    JournalLine save(JournalLine journalLine, Journal journal,WalletAccount walletAccount);
    

    /**
     * Get all the addresses.
     *
     * @return the list of entities.
     */
    List<JournalLine> findAll();


    /**
     * Get the "id" address.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JournalLine> findOne(Long id);
    List<JournalLine> findByWalletAccount_AccountNumber(Long accountNumber);

    void delete(Long id);
}
