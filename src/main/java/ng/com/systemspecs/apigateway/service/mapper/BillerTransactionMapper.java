package ng.com.systemspecs.apigateway.service.mapper;


import ng.com.systemspecs.apigateway.domain.*;
import ng.com.systemspecs.apigateway.service.dto.BillerTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BillerTransaction} and its DTO {@link BillerTransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface BillerTransactionMapper extends EntityMapper<BillerTransactionDTO, BillerTransaction> {

    @Mapping(source = "phoneNumber.id", target = "phoneNumberId")
    BillerTransactionDTO toDto(BillerTransaction billerTransaction);

    @Mapping(source = "phoneNumberId", target = "phoneNumber")
    BillerTransaction toEntity(BillerTransactionDTO billerTransactionDTO);

    default BillerTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        BillerTransaction billerTransaction = new BillerTransaction();
        billerTransaction.setId(id);
        return billerTransaction;
    }
}
