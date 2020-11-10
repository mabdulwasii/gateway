package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.SchemeCategory;
import ng.com.systemspecs.apigateway.service.dto.SchemeCategoryDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-10T07:47:39+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class SchemeCategoryMapperImpl implements SchemeCategoryMapper {

    @Override
    public SchemeCategory toEntity(SchemeCategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SchemeCategory schemeCategory = new SchemeCategory();

        schemeCategory.setId( dto.getId() );
        schemeCategory.setSchemecategoryID( dto.getSchemecategoryID() );
        schemeCategory.setSchemeCategory( dto.getSchemeCategory() );
        schemeCategory.setDescription( dto.getDescription() );

        return schemeCategory;
    }

    @Override
    public SchemeCategoryDTO toDto(SchemeCategory entity) {
        if ( entity == null ) {
            return null;
        }

        SchemeCategoryDTO schemeCategoryDTO = new SchemeCategoryDTO();

        schemeCategoryDTO.setId( entity.getId() );
        schemeCategoryDTO.setSchemecategoryID( entity.getSchemecategoryID() );
        schemeCategoryDTO.setSchemeCategory( entity.getSchemeCategory() );
        schemeCategoryDTO.setDescription( entity.getDescription() );

        return schemeCategoryDTO;
    }

    @Override
    public List<SchemeCategory> toEntity(List<SchemeCategoryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SchemeCategory> list = new ArrayList<SchemeCategory>( dtoList.size() );
        for ( SchemeCategoryDTO schemeCategoryDTO : dtoList ) {
            list.add( toEntity( schemeCategoryDTO ) );
        }

        return list;
    }

    @Override
    public List<SchemeCategoryDTO> toDto(List<SchemeCategory> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SchemeCategoryDTO> list = new ArrayList<SchemeCategoryDTO>( entityList.size() );
        for ( SchemeCategory schemeCategory : entityList ) {
            list.add( toDto( schemeCategory ) );
        }

        return list;
    }
}
