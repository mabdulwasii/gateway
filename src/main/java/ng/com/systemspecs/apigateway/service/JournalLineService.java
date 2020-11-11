package ng.com.systemspecs.apigateway.service;

import java.util.List;
import java.util.Optional;

import ng.com.systemspecs.apigateway.domain.Journal;
import ng.com.systemspecs.apigateway.domain.JournalLine;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.domain.enumeration.PaymentType;

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
    List<JournalLine> findByWalletAccountAccountNumberAndDebitGreaterThan(Long accountNumber,Double debit);
    List<JournalLine> findByWalletAccountAccountNumberAndCreditGreaterThan(Long accountNumber,Double debit);
    List<JournalLine> findAccountDailyTransaction(Long accountNumber);
    List<JournalLine> findCustomerInvoice(PaymentType paymentType);
    Double getAccountDailyTransactionAmount(Long accountNumber);
    void delete(Long id);
}
