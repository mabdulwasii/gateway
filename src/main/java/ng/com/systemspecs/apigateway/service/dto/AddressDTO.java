package ng.com.systemspecs.apigateway.service.dto;

import io.swagger.annotations.ApiModel;
import ng.com.systemspecs.apigateway.domain.Profile;

import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.Address} entity.
 */
@ApiModel(description = "Address of the Actor within the Solution the Latitude and Longitude is critical")
public class AddressDTO implements Serializable {
	private String state;
	private String localGovt;
	private Double latitude;
	private Double longitude;
	private String address;
	private Profile addressOwner;
	
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
	public Profile getAddressOwner() {
		return addressOwner;
	}
	public void setAddressOwner(Profile addressOwner) {
		this.addressOwner = addressOwner;
	}
	@Override
	public String toString() {
		return "AddressDTO [state=" + state + ", localGovt=" + localGovt + ", latitude=" + latitude + ", longitude="
				+ longitude + ", address=" + address + ", addressOwner=" + addressOwner + "]";
	}
	
	
}
