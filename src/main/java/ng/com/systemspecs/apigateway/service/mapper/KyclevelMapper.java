package ng.com.systemspecs.apigateway.service.mapper;


import ng.com.systemspecs.apigateway.domain.*;
import ng.com.systemspecs.apigateway.service.dto.KyclevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Kyclevel} and its DTO {@link KyclevelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KyclevelMapper extends EntityMapper<KyclevelDTO, Kyclevel> {



    default Kyclevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        Kyclevel kyclevel = new Kyclevel();
        kyclevel.setId(id);
        return kyclevel;
    }
}
