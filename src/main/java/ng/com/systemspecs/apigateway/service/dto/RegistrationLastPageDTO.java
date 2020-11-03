package ng.com.systemspecs.apigateway.service.dto;

import java.time.LocalDate;

import ng.com.systemspecs.apigateway.domain.Profile;


public class RegistrationLastPageDTO {
	private LocalDate dateOfBirth;
	private String gender;
	private String photo;
	private String state;
	private String localGovt;
	private Double latitude;
	private Double longitude;
	private String address;
	//private Profile addressOwner;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
//	public Profile getAddressOwner() {
//		return addressOwner;
//	}
//	public void setAddressOwner(Profile addressOwner) {
//		this.addressOwner = addressOwner;
//	}
	@Override
	public String toString() {
		return "RegistrationLastPageDTO [dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", photo=" + photo
				+ ", state=" + state + ", localGovt=" + localGovt + ", latitude=" + latitude + ", longitude="
				+ longitude + ", address=" + address + ", email=" + email + "]";
	}

}
