package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.PaymentTransaction;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-06T08:57:33+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class PaymentTransactionMapperImpl implements PaymentTransactionMapper {

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public List<PaymentTransaction> toEntity(List<PaymentTransactionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PaymentTransaction> list = new ArrayList<PaymentTransaction>( dtoList.size() );
        for ( PaymentTransactionDTO paymentTransactionDTO : dtoList ) {
            list.add( toEntity( paymentTransactionDTO ) );
        }

        return list;
    }

    @Override
    public List<PaymentTransactionDTO> toDto(List<PaymentTransaction> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PaymentTransactionDTO> list = new ArrayList<PaymentTransactionDTO>( entityList.size() );
        for ( PaymentTransaction paymentTransaction : entityList ) {
            list.add( toDto( paymentTransaction ) );
        }

        return list;
    }

    @Override
    public PaymentTransactionDTO toDto(PaymentTransaction paymentTransaction) {
        if ( paymentTransaction == null ) {
            return null;
        }

        PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();

        paymentTransactionDTO.setTransactionOwnerId( paymentTransactionTransactionOwnerId( paymentTransaction ) );
        paymentTransactionDTO.setTransactionOwnerPhoneNumber( paymentTransactionTransactionOwnerPhoneNumber( paymentTransaction ) );
        paymentTransactionDTO.setId( paymentTransaction.getId() );
        paymentTransactionDTO.setPaymenttransID( paymentTransaction.getPaymenttransID() );
        paymentTransactionDTO.setTransactionType( paymentTransaction.getTransactionType() );
        paymentTransactionDTO.setTransactionRef( paymentTransaction.getTransactionRef() );
        paymentTransactionDTO.setAmount( paymentTransaction.getAmount() );
        paymentTransactionDTO.setChannel( paymentTransaction.getChannel() );
        paymentTransactionDTO.setCurrency( paymentTransaction.getCurrency() );
        paymentTransactionDTO.setSourceAccount( paymentTransaction.getSourceAccount() );
        paymentTransactionDTO.setSourceAccountBankCode( paymentTransaction.getSourceAccountBankCode() );
        paymentTransactionDTO.setSourceAccountName( paymentTransaction.getSourceAccountName() );
        paymentTransactionDTO.setSourceNarration( paymentTransaction.getSourceNarration() );
        paymentTransactionDTO.setDestinationAccount( paymentTransaction.getDestinationAccount() );
        paymentTransactionDTO.setDestinationAccountBankCode( paymentTransaction.getDestinationAccountBankCode() );
        paymentTransactionDTO.setDestinationAccountName( paymentTransaction.getDestinationAccountName() );
        paymentTransactionDTO.setDestinationNarration( paymentTransaction.getDestinationNarration() );
        paymentTransactionDTO.setTransactionDate( paymentTransaction.getTransactionDate() );

        return paymentTransactionDTO;
    }

    @Override
    public PaymentTransaction toEntity(PaymentTransactionDTO paymentTransactionDTO) {
        if ( paymentTransactionDTO == null ) {
            return null;
        }

        PaymentTransaction paymentTransaction = new PaymentTransaction();

        paymentTransaction.setTransactionOwner( profileMapper.fromId( paymentTransactionDTO.getTransactionOwnerId() ) );
        paymentTransaction.setId( paymentTransactionDTO.getId() );
        paymentTransaction.setPaymenttransID( paymentTransactionDTO.getPaymenttransID() );
        paymentTransaction.setTransactionType( paymentTransactionDTO.getTransactionType() );
        paymentTransaction.setTransactionRef( paymentTransactionDTO.getTransactionRef() );
        paymentTransaction.setAmount( paymentTransactionDTO.getAmount() );
        paymentTransaction.setChannel( paymentTransactionDTO.getChannel() );
        paymentTransaction.setCurrency( paymentTransactionDTO.getCurrency() );
        paymentTransaction.setSourceAccount( paymentTransactionDTO.getSourceAccount() );
        paymentTransaction.setSourceAccountBankCode( paymentTransactionDTO.getSourceAccountBankCode() );
        paymentTransaction.setSourceAccountName( paymentTransactionDTO.getSourceAccountName() );
        paymentTransaction.setSourceNarration( paymentTransactionDTO.getSourceNarration() );
        paymentTransaction.setDestinationAccount( paymentTransactionDTO.getDestinationAccount() );
        paymentTransaction.setDestinationAccountBankCode( paymentTransactionDTO.getDestinationAccountBankCode() );
        paymentTransaction.setDestinationAccountName( paymentTransactionDTO.getDestinationAccountName() );
        paymentTransaction.setDestinationNarration( paymentTransactionDTO.getDestinationNarration() );
        paymentTransaction.setTransactionDate( paymentTransactionDTO.getTransactionDate() );

        return paymentTransaction;
    }

    private Long paymentTransactionTransactionOwnerId(PaymentTransaction paymentTransaction) {
        if ( paymentTransaction == null ) {
            return null;
        }
        Profile transactionOwner = paymentTransaction.getTransactionOwner();
        if ( transactionOwner == null ) {
            return null;
        }
        Long id = transactionOwner.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String paymentTransactionTransactionOwnerPhoneNumber(PaymentTransaction paymentTransaction) {
        if ( paymentTransaction == null ) {
            return null;
        }
        Profile transactionOwner = paymentTransaction.getTransactionOwner();
        if ( transactionOwner == null ) {
            return null;
        }
        String phoneNumber = transactionOwner.getPhoneNumber();
        if ( phoneNumber == null ) {
            return null;
        }
        return phoneNumber;
    }
}
