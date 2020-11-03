package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.CountrolAccount;
import ng.com.systemspecs.apigateway.service.dto.CountrolAccountDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-03T07:44:04+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class CountrolAccountMapperImpl implements CountrolAccountMapper {

    @Override
    public CountrolAccount toEntity(CountrolAccountDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CountrolAccount countrolAccount = new CountrolAccount();

        countrolAccount.setId( dto.getId() );
        countrolAccount.setCountrolAccountCode( dto.getCountrolAccountCode() );
        countrolAccount.setCountrolAccountName( dto.getCountrolAccountName() );

        return countrolAccount;
    }

    @Override
    public CountrolAccountDTO toDto(CountrolAccount entity) {
        if ( entity == null ) {
            return null;
        }

        CountrolAccountDTO countrolAccountDTO = new CountrolAccountDTO();

        countrolAccountDTO.setId( entity.getId() );
        countrolAccountDTO.setCountrolAccountCode( entity.getCountrolAccountCode() );
        countrolAccountDTO.setCountrolAccountName( entity.getCountrolAccountName() );

        return countrolAccountDTO;
    }

    @Override
    public List<CountrolAccount> toEntity(List<CountrolAccountDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CountrolAccount> list = new ArrayList<CountrolAccount>( dtoList.size() );
        for ( CountrolAccountDTO countrolAccountDTO : dtoList ) {
            list.add( toEntity( countrolAccountDTO ) );
        }

        return list;
    }

    @Override
    public List<CountrolAccountDTO> toDto(List<CountrolAccount> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CountrolAccountDTO> list = new ArrayList<CountrolAccountDTO>( entityList.size() );
        for ( CountrolAccount countrolAccount : entityList ) {
            list.add( toDto( countrolAccount ) );
        }

        return list;
    }
}
