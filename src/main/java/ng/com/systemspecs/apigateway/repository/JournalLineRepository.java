package ng.com.systemspecs.apigateway.repository;

import java.time.LocalDate;
import java.util.List;

import ng.com.systemspecs.apigateway.domain.enumeration.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import ng.com.systemspecs.apigateway.domain.JournalLine;

public interface JournalLineRepository extends JpaRepository<JournalLine, Long>{

    List<JournalLine> findByWalletAccount_AccountNumber(Long accountNumber);
    List<JournalLine> findByWalletAccountAccountNumberAndDebitGreaterThan(Long accountNumber,Double debit);
    List<JournalLine> findByWalletAccountAccountNumberAndCreditGreaterThan(Long accountNumber,Double debit);
    List<JournalLine> findByWalletAccountWalletAccountTypeAndCreditGreaterThan(String walletAccountType,Double debit);
    List<JournalLine> findByWalletAccountAccountNumberAndJounalTransDate(Long accountNumber, LocalDate transDate);
    List<JournalLine> findByWalletAccountAccountOwnerPhoneNumberAndJounalPaymentType(String phoneNumber, PaymentType paymentType);
}
