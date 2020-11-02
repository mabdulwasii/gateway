package ng.com.systemspecs.apigateway.service.mapper;


import ng.com.systemspecs.apigateway.domain.*;
import ng.com.systemspecs.apigateway.service.dto.BillerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Biller} and its DTO {@link BillerDTO}.
 */
@Mapper(componentModel = "spring", uses = {BillerCategoryMapper.class})
public interface BillerMapper extends EntityMapper<BillerDTO, Biller> {

    @Mapping(source = "billerCategory.id", target = "billerCategoryId")
    BillerDTO toDto(Biller biller);

    @Mapping(target = "customersubscriptions", ignore = true)
    @Mapping(target = "removeCustomersubscription", ignore = true)
    @Mapping(target = "billerPlatforms", ignore = true)
    @Mapping(target = "removeBillerPlatform", ignore = true)
    @Mapping(source = "billerCategoryId", target = "billerCategory")
    Biller toEntity(BillerDTO billerDTO);

    default Biller fromId(Long id) {
        if (id == null) {
            return null;
        }
        Biller biller = new Biller();
        biller.setId(id);
        return biller;
    }
}
