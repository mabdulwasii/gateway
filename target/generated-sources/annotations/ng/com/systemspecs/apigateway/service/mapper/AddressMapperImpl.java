package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.Address;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.service.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-21T14:34:38+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_262 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Autowired
    private ProfileMapper profileMapper;

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

        addressDTO.setAddressOwnerId( addressAddressOwnerId( address ) );
        addressDTO.setAddressOwnerPhoneNumber( addressAddressOwnerPhoneNumber( address ) );
        addressDTO.setId( address.getId() );
        addressDTO.setState( address.getState() );
        addressDTO.setLocalGovt( address.getLocalGovt() );
        addressDTO.setLatitude( address.getLatitude() );
        addressDTO.setLongitude( address.getLongitude() );
        addressDTO.setAddress( address.getAddress() );

        return addressDTO;
    }

    @Override
    public Address toEntity(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address address = new Address();

        address.setAddressOwner( profileMapper.fromId( addressDTO.getAddressOwnerId() ) );
        address.setId( addressDTO.getId() );
        address.setState( addressDTO.getState() );
        address.setLocalGovt( addressDTO.getLocalGovt() );
        address.setLatitude( addressDTO.getLatitude() );
        address.setLongitude( addressDTO.getLongitude() );
        address.setAddress( addressDTO.getAddress() );

        return address;
    }

    private Long addressAddressOwnerId(Address address) {
        if ( address == null ) {
            return null;
        }
        Profile addressOwner = address.getAddressOwner();
        if ( addressOwner == null ) {
            return null;
        }
        Long id = addressOwner.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String addressAddressOwnerPhoneNumber(Address address) {
        if ( address == null ) {
            return null;
        }
        Profile addressOwner = address.getAddressOwner();
        if ( addressOwner == null ) {
            return null;
        }
        String phoneNumber = addressOwner.getPhoneNumber();
        if ( phoneNumber == null ) {
            return null;
        }
        return phoneNumber;
    }
}
