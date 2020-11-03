package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.ProfileType;
import ng.com.systemspecs.apigateway.service.dto.ProfileTypeDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-03T09:34:18+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class ProfileTypeMapperImpl implements ProfileTypeMapper {

    @Override
    public ProfileType toEntity(ProfileTypeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProfileType profileType = new ProfileType();

        profileType.setId( dto.getId() );
        profileType.setProfiletypeID( dto.getProfiletypeID() );
        profileType.setProfiletype( dto.getProfiletype() );

        return profileType;
    }

    @Override
    public ProfileTypeDTO toDto(ProfileType entity) {
        if ( entity == null ) {
            return null;
        }

        ProfileTypeDTO profileTypeDTO = new ProfileTypeDTO();

        profileTypeDTO.setId( entity.getId() );
        profileTypeDTO.setProfiletypeID( entity.getProfiletypeID() );
        profileTypeDTO.setProfiletype( entity.getProfiletype() );

        return profileTypeDTO;
    }

    @Override
    public List<ProfileType> toEntity(List<ProfileTypeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ProfileType> list = new ArrayList<ProfileType>( dtoList.size() );
        for ( ProfileTypeDTO profileTypeDTO : dtoList ) {
            list.add( toEntity( profileTypeDTO ) );
        }

        return list;
    }

    @Override
    public List<ProfileTypeDTO> toDto(List<ProfileType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProfileTypeDTO> list = new ArrayList<ProfileTypeDTO>( entityList.size() );
        for ( ProfileType profileType : entityList ) {
            list.add( toDto( profileType ) );
        }

        return list;
    }
}
