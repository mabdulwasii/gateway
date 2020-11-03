package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.WalletAccountType;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountTypeDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-03T09:11:05+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class WalletAccountTypeMapperImpl implements WalletAccountTypeMapper {

    @Override
    public WalletAccountType toEntity(WalletAccountTypeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        WalletAccountType walletAccountType = new WalletAccountType();

        walletAccountType.setId( dto.getId() );
        walletAccountType.setAccountypeID( dto.getAccountypeID() );
        walletAccountType.setWalletAccountType( dto.getWalletAccountType() );

        return walletAccountType;
    }

    @Override
    public WalletAccountTypeDTO toDto(WalletAccountType entity) {
        if ( entity == null ) {
            return null;
        }

        WalletAccountTypeDTO walletAccountTypeDTO = new WalletAccountTypeDTO();

        walletAccountTypeDTO.setId( entity.getId() );
        walletAccountTypeDTO.setAccountypeID( entity.getAccountypeID() );
        walletAccountTypeDTO.setWalletAccountType( entity.getWalletAccountType() );

        return walletAccountTypeDTO;
    }

    @Override
    public List<WalletAccountType> toEntity(List<WalletAccountTypeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<WalletAccountType> list = new ArrayList<WalletAccountType>( dtoList.size() );
        for ( WalletAccountTypeDTO walletAccountTypeDTO : dtoList ) {
            list.add( toEntity( walletAccountTypeDTO ) );
        }

        return list;
    }

    @Override
    public List<WalletAccountTypeDTO> toDto(List<WalletAccountType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<WalletAccountTypeDTO> list = new ArrayList<WalletAccountTypeDTO>( entityList.size() );
        for ( WalletAccountType walletAccountType : entityList ) {
            list.add( toDto( walletAccountType ) );
        }

        return list;
    }
}
