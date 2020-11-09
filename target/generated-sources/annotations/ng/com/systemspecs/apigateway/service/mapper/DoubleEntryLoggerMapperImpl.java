package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.CountrolAccount;
import ng.com.systemspecs.apigateway.domain.DoubleEntryLogger;
import ng.com.systemspecs.apigateway.service.dto.DoubleEntryLoggerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-09T03:59:02+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class DoubleEntryLoggerMapperImpl implements DoubleEntryLoggerMapper {

    @Autowired
    private CountrolAccountMapper countrolAccountMapper;

    @Override
    public List<DoubleEntryLogger> toEntity(List<DoubleEntryLoggerDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DoubleEntryLogger> list = new ArrayList<DoubleEntryLogger>( dtoList.size() );
        for ( DoubleEntryLoggerDTO doubleEntryLoggerDTO : dtoList ) {
            list.add( toEntity( doubleEntryLoggerDTO ) );
        }

        return list;
    }

    @Override
    public List<DoubleEntryLoggerDTO> toDto(List<DoubleEntryLogger> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DoubleEntryLoggerDTO> list = new ArrayList<DoubleEntryLoggerDTO>( entityList.size() );
        for ( DoubleEntryLogger doubleEntryLogger : entityList ) {
            list.add( toDto( doubleEntryLogger ) );
        }

        return list;
    }

    @Override
    public DoubleEntryLoggerDTO toDto(DoubleEntryLogger doubleEntryLogger) {
        if ( doubleEntryLogger == null ) {
            return null;
        }

        DoubleEntryLoggerDTO doubleEntryLoggerDTO = new DoubleEntryLoggerDTO();

        doubleEntryLoggerDTO.setDebitId( doubleEntryLoggerDebitId( doubleEntryLogger ) );
        doubleEntryLoggerDTO.setDebitCountrolAccountCode( doubleEntryLoggerDebitCountrolAccountCode( doubleEntryLogger ) );
        doubleEntryLoggerDTO.setCreditId( doubleEntryLoggerCreditId( doubleEntryLogger ) );
        doubleEntryLoggerDTO.setCreditCountrolAccountCode( doubleEntryLoggerCreditCountrolAccountCode( doubleEntryLogger ) );
        doubleEntryLoggerDTO.setId( doubleEntryLogger.getId() );
        doubleEntryLoggerDTO.setDateEntered( doubleEntryLogger.getDateEntered() );
        doubleEntryLoggerDTO.setDoubleEntryCode( doubleEntryLogger.getDoubleEntryCode() );
        doubleEntryLoggerDTO.setAmount( doubleEntryLogger.getAmount() );
        doubleEntryLoggerDTO.setNarration( doubleEntryLogger.getNarration() );

        return doubleEntryLoggerDTO;
    }

    @Override
    public DoubleEntryLogger toEntity(DoubleEntryLoggerDTO doubleEntryLoggerDTO) {
        if ( doubleEntryLoggerDTO == null ) {
            return null;
        }

        DoubleEntryLogger doubleEntryLogger = new DoubleEntryLogger();

        doubleEntryLogger.setDebit( countrolAccountMapper.fromId( doubleEntryLoggerDTO.getDebitId() ) );
        doubleEntryLogger.setCredit( countrolAccountMapper.fromId( doubleEntryLoggerDTO.getCreditId() ) );
        doubleEntryLogger.setId( doubleEntryLoggerDTO.getId() );
        doubleEntryLogger.setDateEntered( doubleEntryLoggerDTO.getDateEntered() );
        doubleEntryLogger.setDoubleEntryCode( doubleEntryLoggerDTO.getDoubleEntryCode() );
        doubleEntryLogger.setAmount( doubleEntryLoggerDTO.getAmount() );
        doubleEntryLogger.setNarration( doubleEntryLoggerDTO.getNarration() );

        return doubleEntryLogger;
    }

    private Long doubleEntryLoggerDebitId(DoubleEntryLogger doubleEntryLogger) {
        if ( doubleEntryLogger == null ) {
            return null;
        }
        CountrolAccount debit = doubleEntryLogger.getDebit();
        if ( debit == null ) {
            return null;
        }
        Long id = debit.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String doubleEntryLoggerDebitCountrolAccountCode(DoubleEntryLogger doubleEntryLogger) {
        if ( doubleEntryLogger == null ) {
            return null;
        }
        CountrolAccount debit = doubleEntryLogger.getDebit();
        if ( debit == null ) {
            return null;
        }
        String countrolAccountCode = debit.getCountrolAccountCode();
        if ( countrolAccountCode == null ) {
            return null;
        }
        return countrolAccountCode;
    }

    private Long doubleEntryLoggerCreditId(DoubleEntryLogger doubleEntryLogger) {
        if ( doubleEntryLogger == null ) {
            return null;
        }
        CountrolAccount credit = doubleEntryLogger.getCredit();
        if ( credit == null ) {
            return null;
        }
        Long id = credit.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String doubleEntryLoggerCreditCountrolAccountCode(DoubleEntryLogger doubleEntryLogger) {
        if ( doubleEntryLogger == null ) {
            return null;
        }
        CountrolAccount credit = doubleEntryLogger.getCredit();
        if ( credit == null ) {
            return null;
        }
        String countrolAccountCode = credit.getCountrolAccountCode();
        if ( countrolAccountCode == null ) {
            return null;
        }
        return countrolAccountCode;
    }
}
