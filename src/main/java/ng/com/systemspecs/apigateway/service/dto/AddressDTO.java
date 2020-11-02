package ng.com.systemspecs.apigateway.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.Address} entity.
 */
@ApiModel(description = "Address of the Actor within the Solution the Latitude and Longitude is critical")
public class AddressDTO implements Serializable {
    
    private Long id;

    private String state;

    private String localGovt;

    private Double latitude;

    private Double longitude;

    private String address;


    private Long addressOwnerId;

    private String addressOwnerPhoneNumber;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocalGovt() {
        return localGovt;
    }

    public void setLocalGovt(String localGovt) {
        this.localGovt = localGovt;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAddressOwnerId() {
        return addressOwnerId;
    }

    public void setAddressOwnerId(Long profileId) {
        this.addressOwnerId = profileId;
    }

    public String getAddressOwnerPhoneNumber() {
        return addressOwnerPhoneNumber;
    }

    public void setAddressOwnerPhoneNumber(String profilePhoneNumber) {
        this.addressOwnerPhoneNumber = profilePhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressDTO)) {
            return false;
        }

        return id != null && id.equals(((AddressDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + getId() +
            ", state='" + getState() + "'" +
            ", localGovt='" + getLocalGovt() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", address='" + getAddress() + "'" +
            ", addressOwnerId=" + getAddressOwnerId() +
            ", addressOwnerPhoneNumber='" + getAddressOwnerPhoneNumber() + "'" +
            "}";
    }
}
