package ng.com.systemspecs.apigateway.service.mapper;


import ng.com.systemspecs.apigateway.domain.*;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentTransaction} and its DTO {@link PaymentTransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface PaymentTransactionMapper extends EntityMapper<PaymentTransactionDTO, PaymentTransaction> {

    @Mapping(source = "transactionOwner.id", target = "transactionOwnerId")
    @Mapping(source = "transactionOwner.phoneNumber", target = "transactionOwnerPhoneNumber")
    PaymentTransactionDTO toDto(PaymentTransaction paymentTransaction);

    @Mapping(source = "transactionOwnerId", target = "transactionOwner")
    PaymentTransaction toEntity(PaymentTransactionDTO paymentTransactionDTO);

    default PaymentTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setId(id);
        return paymentTransaction;
    }
}
