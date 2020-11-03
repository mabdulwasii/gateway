package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.Biller;
import ng.com.systemspecs.apigateway.domain.BillerCategory;
import ng.com.systemspecs.apigateway.service.dto.BillerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-03T07:44:04+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class BillerMapperImpl implements BillerMapper {

    @Autowired
    private BillerCategoryMapper billerCategoryMapper;

    @Override
    public List<Biller> toEntity(List<BillerDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Biller> list = new ArrayList<Biller>( dtoList.size() );
        for ( BillerDTO billerDTO : dtoList ) {
            list.add( toEntity( billerDTO ) );
        }

        return list;
    }

    @Override
    public List<BillerDTO> toDto(List<Biller> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BillerDTO> list = new ArrayList<BillerDTO>( entityList.size() );
        for ( Biller biller : entityList ) {
            list.add( toDto( biller ) );
        }

        return list;
    }

    @Override
    public BillerDTO toDto(Biller biller) {
        if ( biller == null ) {
            return null;
        }

        BillerDTO billerDTO = new BillerDTO();

        billerDTO.setBillerCategoryId( billerBillerCategoryId( biller ) );
        billerDTO.setId( biller.getId() );
        billerDTO.setBillerID( biller.getBillerID() );
        billerDTO.setBiller( biller.getBiller() );
        billerDTO.setAddress( biller.getAddress() );
        billerDTO.setPhoneNumber( biller.getPhoneNumber() );

        return billerDTO;
    }

    @Override
    public Biller toEntity(BillerDTO billerDTO) {
        if ( billerDTO == null ) {
            return null;
        }

        Biller biller = new Biller();

        biller.setBillerCategory( billerCategoryMapper.fromId( billerDTO.getBillerCategoryId() ) );
        biller.setId( billerDTO.getId() );
        biller.setBillerID( billerDTO.getBillerID() );
        biller.setBiller( billerDTO.getBiller() );
        biller.setAddress( billerDTO.getAddress() );
        biller.setPhoneNumber( billerDTO.getPhoneNumber() );

        return biller;
    }

    private Long billerBillerCategoryId(Biller biller) {
        if ( biller == null ) {
            return null;
        }
        BillerCategory billerCategory = biller.getBillerCategory();
        if ( billerCategory == null ) {
            return null;
        }
        Long id = billerCategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
