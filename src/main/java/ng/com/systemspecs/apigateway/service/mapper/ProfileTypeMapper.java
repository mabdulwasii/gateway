package ng.com.systemspecs.apigateway.service.mapper;


import ng.com.systemspecs.apigateway.domain.*;
import ng.com.systemspecs.apigateway.service.dto.ProfileTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProfileType} and its DTO {@link ProfileTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfileTypeMapper extends EntityMapper<ProfileTypeDTO, ProfileType> {



    default ProfileType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProfileType profileType = new ProfileType();
        profileType.setId(id);
        return profileType;
    }
}
