package ng.com.systemspecs.apigateway.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BillerPlatformMapperTest {

    private BillerPlatformMapper billerPlatformMapper;

    @BeforeEach
    public void setUp() {
        billerPlatformMapper = new BillerPlatformMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(billerPlatformMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(billerPlatformMapper.fromId(null)).isNull();
    }
}
