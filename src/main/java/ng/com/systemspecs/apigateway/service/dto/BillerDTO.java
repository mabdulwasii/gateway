package ng.com.systemspecs.apigateway.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.Biller} entity.
 */
@ApiModel(description = "This setup different Billers in the system a Customer can be in")
public class BillerDTO implements Serializable {
    
    private Long id;

    private Long billerID;

    private String biller;

    private String address;

    private String phoneNumber;


    private Long billerCategoryId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillerID() {
        return billerID;
    }

    public void setBillerID(Long billerID) {
        this.billerID = billerID;
    }

    public String getBiller() {
        return biller;
    }

    public void setBiller(String biller) {
        this.biller = biller;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getBillerCategoryId() {
        return billerCategoryId;
    }

    public void setBillerCategoryId(Long billerCategoryId) {
        this.billerCategoryId = billerCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillerDTO)) {
            return false;
        }

        return id != null && id.equals(((BillerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillerDTO{" +
            "id=" + getId() +
            ", billerID=" + getBillerID() +
            ", biller='" + getBiller() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", billerCategoryId=" + getBillerCategoryId() +
            "}";
    }
}
