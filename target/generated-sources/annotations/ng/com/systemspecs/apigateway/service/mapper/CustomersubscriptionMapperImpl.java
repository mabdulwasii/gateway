package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.Biller;
import ng.com.systemspecs.apigateway.domain.Customersubscription;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.service.dto.CustomersubscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-06T08:57:33+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class CustomersubscriptionMapperImpl implements CustomersubscriptionMapper {

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private BillerMapper billerMapper;

    @Override
    public List<Customersubscription> toEntity(List<CustomersubscriptionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Customersubscription> list = new ArrayList<Customersubscription>( dtoList.size() );
        for ( CustomersubscriptionDTO customersubscriptionDTO : dtoList ) {
            list.add( toEntity( customersubscriptionDTO ) );
        }

        return list;
    }

    @Override
    public List<CustomersubscriptionDTO> toDto(List<Customersubscription> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CustomersubscriptionDTO> list = new ArrayList<CustomersubscriptionDTO>( entityList.size() );
        for ( Customersubscription customersubscription : entityList ) {
            list.add( toDto( customersubscription ) );
        }

        return list;
    }

    @Override
    public CustomersubscriptionDTO toDto(Customersubscription customersubscription) {
        if ( customersubscription == null ) {
            return null;
        }

        CustomersubscriptionDTO customersubscriptionDTO = new CustomersubscriptionDTO();

        customersubscriptionDTO.setBillerId( customersubscriptionBillerId( customersubscription ) );
        customersubscriptionDTO.setPhoneNumberId( customersubscriptionPhoneNumberId( customersubscription ) );
        customersubscriptionDTO.setId( customersubscription.getId() );
        customersubscriptionDTO.setUniqueID( customersubscription.getUniqueID() );
        customersubscriptionDTO.setFrequency( customersubscription.getFrequency() );

        return customersubscriptionDTO;
    }

    @Override
    public Customersubscription toEntity(CustomersubscriptionDTO customersubscriptionDTO) {
        if ( customersubscriptionDTO == null ) {
            return null;
        }

        Customersubscription customersubscription = new Customersubscription();

        customersubscription.setBiller( billerMapper.fromId( customersubscriptionDTO.getBillerId() ) );
        customersubscription.setPhoneNumber( profileMapper.fromId( customersubscriptionDTO.getPhoneNumberId() ) );
        customersubscription.setId( customersubscriptionDTO.getId() );
        customersubscription.setUniqueID( customersubscriptionDTO.getUniqueID() );
        customersubscription.setFrequency( customersubscriptionDTO.getFrequency() );

        return customersubscription;
    }

    private Long customersubscriptionBillerId(Customersubscription customersubscription) {
        if ( customersubscription == null ) {
            return null;
        }
        Biller biller = customersubscription.getBiller();
        if ( biller == null ) {
            return null;
        }
        Long id = biller.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long customersubscriptionPhoneNumberId(Customersubscription customersubscription) {
        if ( customersubscription == null ) {
            return null;
        }
        Profile phoneNumber = customersubscription.getPhoneNumber();
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
