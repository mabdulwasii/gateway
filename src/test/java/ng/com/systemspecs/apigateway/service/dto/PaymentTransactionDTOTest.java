package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class PaymentTransactionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentTransactionDTO.class);
        PaymentTransactionDTO paymentTransactionDTO1 = new PaymentTransactionDTO();
        paymentTransactionDTO1.setId(1L);
        PaymentTransactionDTO paymentTransactionDTO2 = new PaymentTransactionDTO();
        assertThat(paymentTransactionDTO1).isNotEqualTo(paymentTransactionDTO2);
        paymentTransactionDTO2.setId(paymentTransactionDTO1.getId());
        assertThat(paymentTransactionDTO1).isEqualTo(paymentTransactionDTO2);
        paymentTransactionDTO2.setId(2L);
        assertThat(paymentTransactionDTO1).isNotEqualTo(paymentTransactionDTO2);
        paymentTransactionDTO1.setId(null);
        assertThat(paymentTransactionDTO1).isNotEqualTo(paymentTransactionDTO2);
    }
}
