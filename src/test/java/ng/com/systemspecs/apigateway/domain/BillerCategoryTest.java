package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class BillerCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillerCategory.class);
        BillerCategory billerCategory1 = new BillerCategory();
        billerCategory1.setId(1L);
        BillerCategory billerCategory2 = new BillerCategory();
        billerCategory2.setId(billerCategory1.getId());
        assertThat(billerCategory1).isEqualTo(billerCategory2);
        billerCategory2.setId(2L);
        assertThat(billerCategory1).isNotEqualTo(billerCategory2);
        billerCategory1.setId(null);
        assertThat(billerCategory1).isNotEqualTo(billerCategory2);
    }
}
