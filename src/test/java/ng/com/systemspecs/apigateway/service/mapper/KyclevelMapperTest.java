package ng.com.systemspecs.apigateway.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class KyclevelMapperTest {

    private KyclevelMapper kyclevelMapper;

    @BeforeEach
    public void setUp() {
        kyclevelMapper = new KyclevelMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(kyclevelMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(kyclevelMapper.fromId(null)).isNull();
    }
}
