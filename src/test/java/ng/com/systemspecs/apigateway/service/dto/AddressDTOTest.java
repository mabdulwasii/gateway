package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;

import ng.com.systemspecs.apigateway.web.rest.TestUtil;

import static org.assertj.core.api.Assertions.assertThat;


public class AddressDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
       // TestUtil.equalsVerifier(AddressDTO.class);  
        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setAddress("lagos");
        AddressDTO addressDTO2 = new AddressDTO();
        //assertThat(addressDTO1).isNotEqualTo(addressDTO2);
        addressDTO2.setAddress(addressDTO1.getAddress());
        //assertThat(addressDTO1).isEqualTo(addressDTO2);
        addressDTO2.setAddress("ibadan");
        //assertThat(addressDTO1).isNotEqualTo(addressDTO2);
        addressDTO1.setAddress(null);
        //assertThat(addressDTO1).isNotEqualTo(addressDTO2);
    }
}
