package ng.com.systemspecs.apigateway.service.mapper;


import ng.com.systemspecs.apigateway.domain.*;
import ng.com.systemspecs.apigateway.service.dto.SchemeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Scheme} and its DTO {@link SchemeDTO}.
 */
@Mapper(componentModel = "spring", uses = {SchemeCategoryMapper.class})
public interface SchemeMapper extends EntityMapper<SchemeDTO, Scheme> {

    @Mapping(source = "schemeCategory.id", target = "schemeCategoryId")
    SchemeDTO toDto(Scheme scheme);

    @Mapping(source = "schemeCategoryId", target = "schemeCategory")
    Scheme toEntity(SchemeDTO schemeDTO);

    default Scheme fromId(Long id) {
        if (id == null) {
            return null;
        }
        Scheme scheme = new Scheme();
        scheme.setId(id);
        return scheme;
    }
}
