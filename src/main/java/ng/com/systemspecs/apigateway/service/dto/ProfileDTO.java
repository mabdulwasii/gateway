package ng.com.systemspecs.apigateway.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Lob;
import ng.com.systemspecs.apigateway.domain.enumeration.Gender;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.Profile} entity.
 */
@ApiModel(description = "Profile of the user of the Solution this can be of any of the types :- Customer, Agent or Admin")
public class ProfileDTO implements Serializable {
    
    private Long id;

    private String profileID;

    private String phoneNumber;

    private String gender;

    private LocalDate dateOfBirth;

    private String address;

    @Lob
    private byte[] photo;

    private String photoContentType;
    private String bvn;

    private String validID;


    private Long userId;

    private String userLogin;

    private Long profileTypeId;

    private Long kycId;
    
    private String schemeID;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileID() {
        return profileID;
    }

    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getValidID() {
        return validID;
    }

    public void setValidID(String validID) {
        this.validID = validID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getProfileTypeId() {
        return profileTypeId;
    }

    public void setProfileTypeId(Long profileTypeId) {
        this.profileTypeId = profileTypeId;
    }

    public Long getKycId() {
        return kycId;
    }

    public void setKycId(Long kyclevelId) {
        this.kycId = kyclevelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileDTO)) {
            return false;
        }

        return id != null && id.equals(((ProfileDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileDTO{" +
            "id=" + getId() +
            ", profileID='" + getProfileID() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", gender='" + getGender() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", address='" + getAddress() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", bvn='" + getBvn() + "'" +
            ", validID='" + getValidID() + "'" +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            ", profileTypeId=" + getProfileTypeId() +
            ", kycId=" + getKycId() +
            "}";
    }

	public String getSchemeID() {
		return schemeID;
	}

	public void setSchemeID(String schemeID) {
		this.schemeID = schemeID;
	}
}
