package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class CountrolAccountTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CountrolAccount.class);
        CountrolAccount countrolAccount1 = new CountrolAccount();
        countrolAccount1.setId(1L);
        CountrolAccount countrolAccount2 = new CountrolAccount();
        countrolAccount2.setId(countrolAccount1.getId());
        assertThat(countrolAccount1).isEqualTo(countrolAccount2);
        countrolAccount2.setId(2L);
        assertThat(countrolAccount1).isNotEqualTo(countrolAccount2);
        countrolAccount1.setId(null);
        assertThat(countrolAccount1).isNotEqualTo(countrolAccount2);
    }
}
