package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.Biller;
import ng.com.systemspecs.apigateway.domain.BillerPlatform;
import ng.com.systemspecs.apigateway.service.dto.BillerPlatformDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-02T23:39:55+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class BillerPlatformMapperImpl implements BillerPlatformMapper {

    @Autowired
    private BillerMapper billerMapper;

    @Override
    public List<BillerPlatform> toEntity(List<BillerPlatformDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BillerPlatform> list = new ArrayList<BillerPlatform>( dtoList.size() );
        for ( BillerPlatformDTO billerPlatformDTO : dtoList ) {
            list.add( toEntity( billerPlatformDTO ) );
        }

        return list;
    }

    @Override
    public List<BillerPlatformDTO> toDto(List<BillerPlatform> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BillerPlatformDTO> list = new ArrayList<BillerPlatformDTO>( entityList.size() );
        for ( BillerPlatform billerPlatform : entityList ) {
            list.add( toDto( billerPlatform ) );
        }

        return list;
    }

    @Override
    public BillerPlatformDTO toDto(BillerPlatform billerPlatform) {
        if ( billerPlatform == null ) {
            return null;
        }

        BillerPlatformDTO billerPlatformDTO = new BillerPlatformDTO();

        billerPlatformDTO.setBillerId( billerPlatformBillerId( billerPlatform ) );
        billerPlatformDTO.setId( billerPlatform.getId() );
        billerPlatformDTO.setBillerplatformID( billerPlatform.getBillerplatformID() );
        billerPlatformDTO.setBillerPlatform( billerPlatform.getBillerPlatform() );
        billerPlatformDTO.setAmount( billerPlatform.getAmount() );

        return billerPlatformDTO;
    }

    @Override
    public BillerPlatform toEntity(BillerPlatformDTO billerPlatformDTO) {
        if ( billerPlatformDTO == null ) {
            return null;
        }

        BillerPlatform billerPlatform = new BillerPlatform();

        billerPlatform.setBiller( billerMapper.fromId( billerPlatformDTO.getBillerId() ) );
        billerPlatform.setId( billerPlatformDTO.getId() );
        billerPlatform.setBillerplatformID( billerPlatformDTO.getBillerplatformID() );
        billerPlatform.setBillerPlatform( billerPlatformDTO.getBillerPlatform() );
        billerPlatform.setAmount( billerPlatformDTO.getAmount() );

        return billerPlatform;
    }

    private Long billerPlatformBillerId(BillerPlatform billerPlatform) {
        if ( billerPlatform == null ) {
            return null;
        }
        Biller biller = billerPlatform.getBiller();
        if ( biller == null ) {
            return null;
        }
        Long id = biller.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
