package ng.com.systemspecs.apigateway.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ng.com.systemspecs.apigateway.domain.Journal;
import ng.com.systemspecs.apigateway.domain.JournalLine;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.domain.enumeration.PaymentType;
import ng.com.systemspecs.apigateway.repository.JournalLineRepository;
import ng.com.systemspecs.apigateway.service.JournalLineService;

@Service
@Transactional
public class JournalLineServiceImpl implements JournalLineService{

    private final JournalLineRepository journalLineRepository;
    public JournalLineServiceImpl(JournalLineRepository journalLineRepository) {
        this.journalLineRepository=journalLineRepository;
    }

    @Override
    public JournalLine save(JournalLine journalLine, Journal journal, WalletAccount walletAccount) {
        journalLine.setJounal(journal);
        journalLine.setWalletAccount(walletAccount);
        journalLineRepository.save(journalLine);
        return journalLine;
    }

    @Override
    public List<JournalLine> findAll() {
        return journalLineRepository.findAll();
    }

    @Override
    public Optional<JournalLine> findOne(Long id) {
        return journalLineRepository.findById(id);
    }

    @Override
    public List<JournalLine> findByWalletAccount_AccountNumber(Long accountNumber) {
        return journalLineRepository.findByWalletAccount_AccountNumber(accountNumber);
    }

    @Override
    public void delete(Long id) {
        journalLineRepository.deleteById(id);

    }
    @Override
    public List<JournalLine> findByWalletAccountAccountNumberAndDebitGreaterThan(Long accountNumber, Double debit) {
        // TODO Auto-generated method stub
        return journalLineRepository.findByWalletAccountAccountNumberAndDebitGreaterThan(accountNumber, debit);
    }
    @Override
    public List<JournalLine> findByWalletAccountAccountNumberAndCreditGreaterThan(Long accountNumber, Double debit) {
        return journalLineRepository.findByWalletAccountAccountNumberAndCreditGreaterThan(accountNumber, debit);
    }
    @Override
    public List<JournalLine> findAccountDailyTransaction(Long accountNumber) {
        return journalLineRepository.findByWalletAccountAccountNumberAndJounalTransDate(accountNumber, LocalDate.now());
    }
    @Override
    public Double getAccountDailyTransactionAmount(Long accountNumber) {
        List<JournalLine> dailyTrans = findAccountDailyTransaction(accountNumber);
        Double amount = 0.00;
        for (JournalLine journalLine : dailyTrans) {
            amount += journalLine.getDebit();
        }
        return amount;
    }
    @Override
    public List<JournalLine> findCustomerInvoice(PaymentType paymentType) {
        // TODO Auto-generated method stub
        String phoneNumber = null;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            phoneNumber = ((UserDetails)principal).getUsername();
        }else
            phoneNumber = principal.toString();


        return journalLineRepository.findByWalletAccountAccountOwnerPhoneNumberAndJounalPaymentType(phoneNumber, paymentType.INVOICE);
    }

}
