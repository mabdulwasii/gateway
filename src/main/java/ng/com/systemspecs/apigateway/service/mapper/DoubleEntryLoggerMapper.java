package ng.com.systemspecs.apigateway.service.mapper;


import ng.com.systemspecs.apigateway.domain.*;
import ng.com.systemspecs.apigateway.service.dto.DoubleEntryLoggerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DoubleEntryLogger} and its DTO {@link DoubleEntryLoggerDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountrolAccountMapper.class})
public interface DoubleEntryLoggerMapper extends EntityMapper<DoubleEntryLoggerDTO, DoubleEntryLogger> {

    @Mapping(source = "debit.id", target = "debitId")
    @Mapping(source = "debit.countrolAccountCode", target = "debitCountrolAccountCode")
    @Mapping(source = "credit.id", target = "creditId")
    @Mapping(source = "credit.countrolAccountCode", target = "creditCountrolAccountCode")
    DoubleEntryLoggerDTO toDto(DoubleEntryLogger doubleEntryLogger);

    @Mapping(source = "debitId", target = "debit")
    @Mapping(source = "creditId", target = "credit")
    DoubleEntryLogger toEntity(DoubleEntryLoggerDTO doubleEntryLoggerDTO);

    default DoubleEntryLogger fromId(Long id) {
        if (id == null) {
            return null;
        }
        DoubleEntryLogger doubleEntryLogger = new DoubleEntryLogger();
        doubleEntryLogger.setId(id);
        return doubleEntryLogger;
    }
}
