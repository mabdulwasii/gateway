package ng.com.systemspecs.apigateway.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WalletAccountMapperTest {

    private WalletAccountMapper walletAccountMapper;

    @BeforeEach
    public void setUp() {
        walletAccountMapper = new WalletAccountMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(walletAccountMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(walletAccountMapper.fromId(null)).isNull();
    }
}
