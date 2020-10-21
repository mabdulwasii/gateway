package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class CustomersubscriptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Customersubscription.class);
        Customersubscription customersubscription1 = new Customersubscription();
        customersubscription1.setId(1L);
        Customersubscription customersubscription2 = new Customersubscription();
        customersubscription2.setId(customersubscription1.getId());
        assertThat(customersubscription1).isEqualTo(customersubscription2);
        customersubscription2.setId(2L);
        assertThat(customersubscription1).isNotEqualTo(customersubscription2);
        customersubscription1.setId(null);
        assertThat(customersubscription1).isNotEqualTo(customersubscription2);
    }
}
