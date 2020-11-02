package ng.com.systemspecs.apigateway.service.mapper;


import ng.com.systemspecs.apigateway.domain.*;
import ng.com.systemspecs.apigateway.service.dto.BillerCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BillerCategory} and its DTO {@link BillerCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BillerCategoryMapper extends EntityMapper<BillerCategoryDTO, BillerCategory> {


    @Mapping(target = "billers", ignore = true)
    @Mapping(target = "removeBiller", ignore = true)
    BillerCategory toEntity(BillerCategoryDTO billerCategoryDTO);

    default BillerCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        BillerCategory billerCategory = new BillerCategory();
        billerCategory.setId(id);
        return billerCategory;
    }
}
