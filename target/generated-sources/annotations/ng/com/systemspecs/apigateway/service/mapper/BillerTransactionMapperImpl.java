package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.BillerTransaction;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.service.dto.BillerTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-11T18:42:00+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
@Component
public class BillerTransactionMapperImpl implements BillerTransactionMapper {

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public List<BillerTransaction> toEntity(List<BillerTransactionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BillerTransaction> list = new ArrayList<BillerTransaction>( dtoList.size() );
        for ( BillerTransactionDTO billerTransactionDTO : dtoList ) {
            list.add( toEntity( billerTransactionDTO ) );
        }

        return list;
    }

    @Override
    public List<BillerTransactionDTO> toDto(List<BillerTransaction> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BillerTransactionDTO> list = new ArrayList<BillerTransactionDTO>( entityList.size() );
        for ( BillerTransaction billerTransaction : entityList ) {
            list.add( toDto( billerTransaction ) );
        }

        return list;
    }

    @Override
    public BillerTransactionDTO toDto(BillerTransaction billerTransaction) {
        if ( billerTransaction == null ) {
            return null;
        }

        BillerTransactionDTO billerTransactionDTO = new BillerTransactionDTO();

        billerTransactionDTO.setPhoneNumberId( billerTransactionPhoneNumberId( billerTransaction ) );
        billerTransactionDTO.setId( billerTransaction.getId() );
        billerTransactionDTO.setBillertransID( billerTransaction.getBillertransID() );
        billerTransactionDTO.setTransactionRef( billerTransaction.getTransactionRef() );
        billerTransactionDTO.setNarration( billerTransaction.getNarration() );
        billerTransactionDTO.setAmount( billerTransaction.getAmount() );

        return billerTransactionDTO;
    }

    @Override
    public BillerTransaction toEntity(BillerTransactionDTO billerTransactionDTO) {
        if ( billerTransactionDTO == null ) {
            return null;
        }

        BillerTransaction billerTransaction = new BillerTransaction();

        billerTransaction.setPhoneNumber( profileMapper.fromId( billerTransactionDTO.getPhoneNumberId() ) );
        billerTransaction.setId( billerTransactionDTO.getId() );
        billerTransaction.setBillertransID( billerTransactionDTO.getBillertransID() );
        billerTransaction.setTransactionRef( billerTransactionDTO.getTransactionRef() );
        billerTransaction.setNarration( billerTransactionDTO.getNarration() );
        billerTransaction.setAmount( billerTransactionDTO.getAmount() );

        return billerTransaction;
    }

    private Long billerTransactionPhoneNumberId(BillerTransaction billerTransaction) {
        if ( billerTransaction == null ) {
            return null;
        }
        Profile phoneNumber = billerTransaction.getPhoneNumber();
        if ( phoneNumber == null ) {
            return null;
        }
        Long id = phoneNumber.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
