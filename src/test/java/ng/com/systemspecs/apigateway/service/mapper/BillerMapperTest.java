package ng.com.systemspecs.apigateway.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BillerMapperTest {

    private BillerMapper billerMapper;

    @BeforeEach
    public void setUp() {
        billerMapper = new BillerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(billerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(billerMapper.fromId(null)).isNull();
    }
}
