package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.Kyclevel;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.ProfileType;
import ng.com.systemspecs.apigateway.domain.User;
import ng.com.systemspecs.apigateway.domain.enumeration.Gender;
import ng.com.systemspecs.apigateway.service.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-10T07:47:39+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProfileTypeMapper profileTypeMapper;
    @Autowired
    private KyclevelMapper kyclevelMapper;

    @Override
    public List<Profile> toEntity(List<ProfileDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Profile> list = new ArrayList<Profile>( dtoList.size() );
        for ( ProfileDTO profileDTO : dtoList ) {
            list.add( toEntity( profileDTO ) );
        }

        return list;
    }

    @Override
    public List<ProfileDTO> toDto(List<Profile> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProfileDTO> list = new ArrayList<ProfileDTO>( entityList.size() );
        for ( Profile profile : entityList ) {
            list.add( toDto( profile ) );
        }

        return list;
    }

    @Override
    public ProfileDTO toDto(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setUserLogin( profileUserLogin( profile ) );
        profileDTO.setProfileTypeId( profileProfileTypeId( profile ) );
        profileDTO.setKycId( profileKycId( profile ) );
        profileDTO.setUserId( profileUserId( profile ) );
        profileDTO.setId( profile.getId() );
        profileDTO.setProfileID( profile.getProfileID() );
        profileDTO.setPhoneNumber( profile.getPhoneNumber() );
        if ( profile.getGender() != null ) {
            profileDTO.setGender( profile.getGender().name() );
        }
        profileDTO.setDateOfBirth( profile.getDateOfBirth() );
        byte[] photo = profile.getPhoto();
        if ( photo != null ) {
            profileDTO.setPhoto( Arrays.copyOf( photo, photo.length ) );
        }
        profileDTO.setPhotoContentType( profile.getPhotoContentType() );
        profileDTO.setBvn( profile.getBvn() );
        profileDTO.setValidID( profile.getValidID() );

        return profileDTO;
    }

    @Override
    public Profile toEntity(ProfileDTO profileDTO) {
        if ( profileDTO == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setProfileType( profileTypeMapper.fromId( profileDTO.getProfileTypeId() ) );
        profile.setKyc( kyclevelMapper.fromId( profileDTO.getKycId() ) );
        profile.setUser( userMapper.userFromId( profileDTO.getUserId() ) );
        profile.setId( profileDTO.getId() );
        profile.setProfileID( profileDTO.getProfileID() );
        profile.setPhoneNumber( profileDTO.getPhoneNumber() );
        if ( profileDTO.getGender() != null ) {
            profile.setGender( Enum.valueOf( Gender.class, profileDTO.getGender() ) );
        }
        profile.setDateOfBirth( profileDTO.getDateOfBirth() );
        byte[] photo = profileDTO.getPhoto();
        if ( photo != null ) {
            profile.setPhoto( Arrays.copyOf( photo, photo.length ) );
        }
        profile.setPhotoContentType( profileDTO.getPhotoContentType() );
        profile.setBvn( profileDTO.getBvn() );
        profile.setValidID( profileDTO.getValidID() );

        return profile;
    }

    private String profileUserLogin(Profile profile) {
        if ( profile == null ) {
            return null;
        }
        User user = profile.getUser();
        if ( user == null ) {
            return null;
        }
        String login = user.getLogin();
        if ( login == null ) {
            return null;
        }
        return login;
    }

    private Long profileProfileTypeId(Profile profile) {
        if ( profile == null ) {
            return null;
        }
        ProfileType profileType = profile.getProfileType();
        if ( profileType == null ) {
            return null;
        }
        Long id = profileType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long profileKycId(Profile profile) {
        if ( profile == null ) {
            return null;
        }
        Kyclevel kyc = profile.getKyc();
        if ( kyc == null ) {
            return null;
        }
        Long id = kyc.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long profileUserId(Profile profile) {
        if ( profile == null ) {
            return null;
        }
        User user = profile.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
