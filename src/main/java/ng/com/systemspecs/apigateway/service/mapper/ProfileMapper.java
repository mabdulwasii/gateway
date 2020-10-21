package ng.com.systemspecs.apigateway.service.mapper;


import ng.com.systemspecs.apigateway.domain.*;
import ng.com.systemspecs.apigateway.service.dto.ProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profile} and its DTO {@link ProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ProfileTypeMapper.class, KyclevelMapper.class})
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "profileType.id", target = "profileTypeId")
    @Mapping(source = "kyc.id", target = "kycId")
    ProfileDTO toDto(Profile profile);

    @Mapping(target = "walletAccounts", ignore = true)
    @Mapping(target = "removeWalletAccount", ignore = true)
    @Mapping(target = "paymentTransactions", ignore = true)
    @Mapping(target = "removePaymentTransaction", ignore = true)
    @Mapping(target = "billerTransactions", ignore = true)
    @Mapping(target = "removeBillerTransaction", ignore = true)
    @Mapping(target = "customersubscriptions", ignore = true)
    @Mapping(target = "removeCustomersubscription", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "profileTypeId", target = "profileType")
    @Mapping(source = "kycId", target = "kyc")
    Profile toEntity(ProfileDTO profileDTO);

    default Profile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setId(id);
        return profile;
    }
}
