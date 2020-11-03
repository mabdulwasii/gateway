package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.BillerCategory;
import ng.com.systemspecs.apigateway.service.dto.BillerCategoryDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-03T09:11:05+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class BillerCategoryMapperImpl implements BillerCategoryMapper {

    @Override
    public BillerCategoryDTO toDto(BillerCategory entity) {
        if ( entity == null ) {
            return null;
        }

        BillerCategoryDTO billerCategoryDTO = new BillerCategoryDTO();

        billerCategoryDTO.setId( entity.getId() );
        billerCategoryDTO.setBillercategoryID( entity.getBillercategoryID() );
        billerCategoryDTO.setBillerCategory( entity.getBillerCategory() );

        return billerCategoryDTO;
    }

    @Override
    public List<BillerCategory> toEntity(List<BillerCategoryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BillerCategory> list = new ArrayList<BillerCategory>( dtoList.size() );
        for ( BillerCategoryDTO billerCategoryDTO : dtoList ) {
            list.add( toEntity( billerCategoryDTO ) );
        }

        return list;
    }

    @Override
    public List<BillerCategoryDTO> toDto(List<BillerCategory> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BillerCategoryDTO> list = new ArrayList<BillerCategoryDTO>( entityList.size() );
        for ( BillerCategory billerCategory : entityList ) {
            list.add( toDto( billerCategory ) );
        }

        return list;
    }

    @Override
    public BillerCategory toEntity(BillerCategoryDTO billerCategoryDTO) {
        if ( billerCategoryDTO == null ) {
            return null;
        }

        BillerCategory billerCategory = new BillerCategory();

        billerCategory.setId( billerCategoryDTO.getId() );
        billerCategory.setBillercategoryID( billerCategoryDTO.getBillercategoryID() );
        billerCategory.setBillerCategory( billerCategoryDTO.getBillerCategory() );

        return billerCategory;
    }
}
