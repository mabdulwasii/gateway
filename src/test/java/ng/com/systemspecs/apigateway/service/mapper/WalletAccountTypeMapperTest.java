package ng.com.systemspecs.apigateway.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WalletAccountTypeMapperTest {

    private WalletAccountTypeMapper walletAccountTypeMapper;

    @BeforeEach
    public void setUp() {
        walletAccountTypeMapper = new WalletAccountTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(walletAccountTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(walletAccountTypeMapper.fromId(null)).isNull();
    }
}
