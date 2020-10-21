package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class BillerCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillerCategoryDTO.class);
        BillerCategoryDTO billerCategoryDTO1 = new BillerCategoryDTO();
        billerCategoryDTO1.setId(1L);
        BillerCategoryDTO billerCategoryDTO2 = new BillerCategoryDTO();
        assertThat(billerCategoryDTO1).isNotEqualTo(billerCategoryDTO2);
        billerCategoryDTO2.setId(billerCategoryDTO1.getId());
        assertThat(billerCategoryDTO1).isEqualTo(billerCategoryDTO2);
        billerCategoryDTO2.setId(2L);
        assertThat(billerCategoryDTO1).isNotEqualTo(billerCategoryDTO2);
        billerCategoryDTO1.setId(null);
        assertThat(billerCategoryDTO1).isNotEqualTo(billerCategoryDTO2);
    }
}
