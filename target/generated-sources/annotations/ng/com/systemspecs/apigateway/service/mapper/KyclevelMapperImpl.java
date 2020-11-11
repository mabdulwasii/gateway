package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.Kyclevel;
import ng.com.systemspecs.apigateway.service.dto.KyclevelDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-11T18:42:00+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
@Component
public class KyclevelMapperImpl implements KyclevelMapper {

    @Override
    public Kyclevel toEntity(KyclevelDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Kyclevel kyclevel = new Kyclevel();

        kyclevel.setId( dto.getId() );
        kyclevel.setKycID( dto.getKycID() );
        kyclevel.setKyc( dto.getKyc() );
        kyclevel.setDescription( dto.getDescription() );
        kyclevel.setKycLevel( dto.getKycLevel() );
        kyclevel.setPhoneNumber( dto.isPhoneNumber() );
        kyclevel.setEmailAddress( dto.isEmailAddress() );
        kyclevel.setFirstName( dto.isFirstName() );
        kyclevel.setLastName( dto.isLastName() );
        kyclevel.setGender( dto.isGender() );
        kyclevel.setDateofBirth( dto.isDateofBirth() );
        kyclevel.setAddress( dto.isAddress() );
        kyclevel.setPhotoUpload( dto.isPhotoUpload() );
        kyclevel.setVerifiedBVN( dto.isVerifiedBVN() );
        kyclevel.setVerifiedValidID( dto.isVerifiedValidID() );
        kyclevel.setEvidenceofAddress( dto.isEvidenceofAddress() );
        kyclevel.setVerificationofAddress( dto.isVerificationofAddress() );
        kyclevel.setEmploymentDetails( dto.isEmploymentDetails() );
        kyclevel.setDailyTransactionLimit( dto.getDailyTransactionLimit() );
        kyclevel.setCumulativeBalanceLimit( dto.getCumulativeBalanceLimit() );
        kyclevel.setPaymentTransaction( dto.isPaymentTransaction() );
        kyclevel.setBillerTransaction( dto.isBillerTransaction() );

        return kyclevel;
    }

    @Override
    public KyclevelDTO toDto(Kyclevel entity) {
        if ( entity == null ) {
            return null;
        }

        KyclevelDTO kyclevelDTO = new KyclevelDTO();

        kyclevelDTO.setId( entity.getId() );
        kyclevelDTO.setKycID( entity.getKycID() );
        kyclevelDTO.setKyc( entity.getKyc() );
        kyclevelDTO.setDescription( entity.getDescription() );
        kyclevelDTO.setKycLevel( entity.getKycLevel() );
        kyclevelDTO.setPhoneNumber( entity.isPhoneNumber() );
        kyclevelDTO.setEmailAddress( entity.isEmailAddress() );
        kyclevelDTO.setFirstName( entity.isFirstName() );
        kyclevelDTO.setLastName( entity.isLastName() );
        kyclevelDTO.setGender( entity.isGender() );
        kyclevelDTO.setDateofBirth( entity.isDateofBirth() );
        kyclevelDTO.setAddress( entity.isAddress() );
        kyclevelDTO.setPhotoUpload( entity.isPhotoUpload() );
        kyclevelDTO.setVerifiedBVN( entity.isVerifiedBVN() );
        kyclevelDTO.setVerifiedValidID( entity.isVerifiedValidID() );
        kyclevelDTO.setEvidenceofAddress( entity.isEvidenceofAddress() );
        kyclevelDTO.setVerificationofAddress( entity.isVerificationofAddress() );
        kyclevelDTO.setEmploymentDetails( entity.isEmploymentDetails() );
        kyclevelDTO.setDailyTransactionLimit( entity.getDailyTransactionLimit() );
        kyclevelDTO.setCumulativeBalanceLimit( entity.getCumulativeBalanceLimit() );
        kyclevelDTO.setPaymentTransaction( entity.isPaymentTransaction() );
        kyclevelDTO.setBillerTransaction( entity.isBillerTransaction() );

        return kyclevelDTO;
    }

    @Override
    public List<Kyclevel> toEntity(List<KyclevelDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Kyclevel> list = new ArrayList<Kyclevel>( dtoList.size() );
        for ( KyclevelDTO kyclevelDTO : dtoList ) {
            list.add( toEntity( kyclevelDTO ) );
        }

        return list;
    }

    @Override
    public List<KyclevelDTO> toDto(List<Kyclevel> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<KyclevelDTO> list = new ArrayList<KyclevelDTO>( entityList.size() );
        for ( Kyclevel kyclevel : entityList ) {
            list.add( toDto( kyclevel ) );
        }

        return list;
    }
}
