package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.Address;
import ng.com.systemspecs.apigateway.service.dto.AddressDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-06T08:57:33+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toEntity(AddressDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Address address = new Address();

        address.setState( dto.getState() );
        address.setLocalGovt( dto.getLocalGovt() );
        address.setLatitude( dto.getLatitude() );
        address.setLongitude( dto.getLongitude() );
        address.setAddress( dto.getAddress() );
        address.setAddressOwner( dto.getAddressOwner() );

        return address;
    }

    @Override
    public List<Address> toEntity(List<AddressDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Address> list = new ArrayList<Address>( dtoList.size() );
        for ( AddressDTO addressDTO : dtoList ) {
            list.add( toEntity( addressDTO ) );
        }

        return list;
    }

    @Override
    public List<AddressDTO> toDto(List<Address> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AddressDTO> list = new ArrayList<AddressDTO>( entityList.size() );
        for ( Address address : entityList ) {
            list.add( toDto( address ) );
        }

        return list;
    }

    @Override
    public AddressDTO toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setState( address.getState() );
        addressDTO.setLocalGovt( address.getLocalGovt() );
        addressDTO.setLatitude( address.getLatitude() );
        addressDTO.setLongitude( address.getLongitude() );
        addressDTO.setAddress( address.getAddress() );
        addressDTO.setAddressOwner( address.getAddressOwner() );

        return addressDTO;
    }
}
