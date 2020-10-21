package ng.com.systemspecs.apigateway.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTransactionMapperTest {

    private PaymentTransactionMapper paymentTransactionMapper;

    @BeforeEach
    public void setUp() {
        paymentTransactionMapper = new PaymentTransactionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(paymentTransactionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(paymentTransactionMapper.fromId(null)).isNull();
    }
}
