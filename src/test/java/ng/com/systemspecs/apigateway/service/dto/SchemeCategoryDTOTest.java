package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class SchemeCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SchemeCategoryDTO.class);
        SchemeCategoryDTO schemeCategoryDTO1 = new SchemeCategoryDTO();
        schemeCategoryDTO1.setId(1L);
        SchemeCategoryDTO schemeCategoryDTO2 = new SchemeCategoryDTO();
        assertThat(schemeCategoryDTO1).isNotEqualTo(schemeCategoryDTO2);
        schemeCategoryDTO2.setId(schemeCategoryDTO1.getId());
        assertThat(schemeCategoryDTO1).isEqualTo(schemeCategoryDTO2);
        schemeCategoryDTO2.setId(2L);
        assertThat(schemeCategoryDTO1).isNotEqualTo(schemeCategoryDTO2);
        schemeCategoryDTO1.setId(null);
        assertThat(schemeCategoryDTO1).isNotEqualTo(schemeCategoryDTO2);
    }
}
