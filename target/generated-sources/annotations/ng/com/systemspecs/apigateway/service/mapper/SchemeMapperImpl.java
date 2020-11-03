package ng.com.systemspecs.apigateway.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ng.com.systemspecs.apigateway.domain.Scheme;
import ng.com.systemspecs.apigateway.domain.SchemeCategory;
import ng.com.systemspecs.apigateway.service.dto.SchemeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-03T09:11:05+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class SchemeMapperImpl implements SchemeMapper {

    @Autowired
    private SchemeCategoryMapper schemeCategoryMapper;

    @Override
    public List<Scheme> toEntity(List<SchemeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Scheme> list = new ArrayList<Scheme>( dtoList.size() );
        for ( SchemeDTO schemeDTO : dtoList ) {
            list.add( toEntity( schemeDTO ) );
        }

        return list;
    }

    @Override
    public List<SchemeDTO> toDto(List<Scheme> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SchemeDTO> list = new ArrayList<SchemeDTO>( entityList.size() );
        for ( Scheme scheme : entityList ) {
            list.add( toDto( scheme ) );
        }

        return list;
    }

    @Override
    public SchemeDTO toDto(Scheme scheme) {
        if ( scheme == null ) {
            return null;
        }

        SchemeDTO schemeDTO = new SchemeDTO();

        schemeDTO.setSchemeCategoryId( schemeSchemeCategoryId( scheme ) );
        schemeDTO.setId( scheme.getId() );
        schemeDTO.setSchemeID( scheme.getSchemeID() );
        schemeDTO.setScheme( scheme.getScheme() );

        return schemeDTO;
    }

    @Override
    public Scheme toEntity(SchemeDTO schemeDTO) {
        if ( schemeDTO == null ) {
            return null;
        }

        Scheme scheme = new Scheme();

        scheme.setSchemeCategory( schemeCategoryMapper.fromId( schemeDTO.getSchemeCategoryId() ) );
        scheme.setId( schemeDTO.getId() );
        scheme.setSchemeID( schemeDTO.getSchemeID() );
        scheme.setScheme( schemeDTO.getScheme() );

        return scheme;
    }

    private Long schemeSchemeCategoryId(Scheme scheme) {
        if ( scheme == null ) {
            return null;
        }
        SchemeCategory schemeCategory = scheme.getSchemeCategory();
        if ( schemeCategory == null ) {
            return null;
        }
        Long id = schemeCategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
