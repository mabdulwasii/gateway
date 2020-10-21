package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.Scheme;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.domain.WalletAccountType;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-21T14:34:38+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_262 (Oracle Corporation)"
)
@Component
public class WalletAccountMapperImpl implements WalletAccountMapper {

    @Autowired
    private SchemeMapper schemeMapper;
    @Autowired
    private WalletAccountTypeMapper walletAccountTypeMapper;
    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public List<WalletAccount> toEntity(List<WalletAccountDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<WalletAccount> list = new ArrayList<WalletAccount>( dtoList.size() );
        for ( WalletAccountDTO walletAccountDTO : dtoList ) {
            list.add( toEntity( walletAccountDTO ) );
        }

        return list;
    }

    @Override
    public List<WalletAccountDTO> toDto(List<WalletAccount> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<WalletAccountDTO> list = new ArrayList<WalletAccountDTO>( entityList.size() );
        for ( WalletAccount walletAccount : entityList ) {
            list.add( toDto( walletAccount ) );
        }

        return list;
    }

    @Override
    public WalletAccountDTO toDto(WalletAccount walletAccount) {
        if ( walletAccount == null ) {
            return null;
        }

        WalletAccountDTO walletAccountDTO = new WalletAccountDTO();

        walletAccountDTO.setSchemeId( walletAccountSchemeId( walletAccount ) );
        walletAccountDTO.setWalletAccountTypeId( walletAccountWalletAccountTypeId( walletAccount ) );
        walletAccountDTO.setAccountOwnerPhoneNumber( walletAccountAccountOwnerPhoneNumber( walletAccount ) );
        walletAccountDTO.setAccountOwnerId( walletAccountAccountOwnerId( walletAccount ) );
        walletAccountDTO.setAccountName( walletAccount.getAccountName() );
        walletAccountDTO.setId( walletAccount.getId() );
        walletAccountDTO.setAccountNumber( walletAccount.getAccountNumber() );
        walletAccountDTO.setCurrentBalance( walletAccount.getCurrentBalance() );
        walletAccountDTO.setDateOpened( walletAccount.getDateOpened() );

        return walletAccountDTO;
    }

    @Override
    public WalletAccount toEntity(WalletAccountDTO walletAccountDTO) {
        if ( walletAccountDTO == null ) {
            return null;
        }

        WalletAccount walletAccount = new WalletAccount();

        walletAccount.setWalletAccountType( walletAccountTypeMapper.fromId( walletAccountDTO.getWalletAccountTypeId() ) );
        walletAccount.setAccountOwner( profileMapper.fromId( walletAccountDTO.getAccountOwnerId() ) );
        walletAccount.setScheme( schemeMapper.fromId( walletAccountDTO.getSchemeId() ) );
        walletAccount.setAccountName( walletAccountDTO.getAccountName() );
        walletAccount.setId( walletAccountDTO.getId() );
        walletAccount.setAccountNumber( walletAccountDTO.getAccountNumber() );
        walletAccount.setCurrentBalance( walletAccountDTO.getCurrentBalance() );
        walletAccount.setDateOpened( walletAccountDTO.getDateOpened() );

        return walletAccount;
    }

    private Long walletAccountSchemeId(WalletAccount walletAccount) {
        if ( walletAccount == null ) {
            return null;
        }
        Scheme scheme = walletAccount.getScheme();
        if ( scheme == null ) {
            return null;
        }
        Long id = scheme.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long walletAccountWalletAccountTypeId(WalletAccount walletAccount) {
        if ( walletAccount == null ) {
            return null;
        }
        WalletAccountType walletAccountType = walletAccount.getWalletAccountType();
        if ( walletAccountType == null ) {
            return null;
        }
        Long id = walletAccountType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String walletAccountAccountOwnerPhoneNumber(WalletAccount walletAccount) {
        if ( walletAccount == null ) {
            return null;
        }
        Profile accountOwner = walletAccount.getAccountOwner();
        if ( accountOwner == null ) {
            return null;
        }
        String phoneNumber = accountOwner.getPhoneNumber();
        if ( phoneNumber == null ) {
            return null;
        }
        return phoneNumber;
    }

    private Long walletAccountAccountOwnerId(WalletAccount walletAccount) {
        if ( walletAccount == null ) {
            return null;
        }
        Profile accountOwner = walletAccount.getAccountOwner();
        if ( accountOwner == null ) {
            return null;
        }
        Long id = accountOwner.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
