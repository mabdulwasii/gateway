package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class PaymentTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentTransaction.class);
        PaymentTransaction paymentTransaction1 = new PaymentTransaction();
        paymentTransaction1.setId(1L);
        PaymentTransaction paymentTransaction2 = new PaymentTransaction();
        paymentTransaction2.setId(paymentTransaction1.getId());
        assertThat(paymentTransaction1).isEqualTo(paymentTransaction2);
        paymentTransaction2.setId(2L);
        assertThat(paymentTransaction1).isNotEqualTo(paymentTransaction2);
        paymentTransaction1.setId(null);
        assertThat(paymentTransaction1).isNotEqualTo(paymentTransaction2);
    }
}
