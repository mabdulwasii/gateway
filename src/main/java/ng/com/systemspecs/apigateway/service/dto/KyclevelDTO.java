package ng.com.systemspecs.apigateway.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.Kyclevel} entity.
 */
@ApiModel(description = "This setup different KYC Level a Customer can be in")
public class KyclevelDTO implements Serializable {
    
    private Long id;

    private Long kycID;

    private String kyc;

    private String description;

    private Integer kycLevel;

    private Boolean phoneNumber;

    private Boolean emailAddress;

    private Boolean firstName;

    private Boolean lastName;

    private Boolean gender;

    private Boolean dateofBirth;

    private Boolean address;

    private Boolean photoUpload;

    private Boolean verifiedBVN;

    private Boolean verifiedValidID;

    private Boolean evidenceofAddress;

    private Boolean verificationofAddress;

    private Boolean employmentDetails;

    private Double dailyTransactionLimit;

    private Double cumulativeBalanceLimit;

    private Boolean paymentTransaction;

    private Boolean billerTransaction;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKycID() {
        return kycID;
    }

    public void setKycID(Long kycID) {
        this.kycID = kycID;
    }

    public String getKyc() {
        return kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getKycLevel() {
        return kycLevel;
    }

    public void setKycLevel(Integer kycLevel) {
        this.kycLevel = kycLevel;
    }

    public Boolean isPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Boolean phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean isEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(Boolean emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean isFirstName() {
        return firstName;
    }

    public void setFirstName(Boolean firstName) {
        this.firstName = firstName;
    }

    public Boolean isLastName() {
        return lastName;
    }

    public void setLastName(Boolean lastName) {
        this.lastName = lastName;
    }

    public Boolean isGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean isDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Boolean dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public Boolean isAddress() {
        return address;
    }

    public void setAddress(Boolean address) {
        this.address = address;
    }

    public Boolean isPhotoUpload() {
        return photoUpload;
    }

    public void setPhotoUpload(Boolean photoUpload) {
        this.photoUpload = photoUpload;
    }

    public Boolean isVerifiedBVN() {
        return verifiedBVN;
    }

    public void setVerifiedBVN(Boolean verifiedBVN) {
        this.verifiedBVN = verifiedBVN;
    }

    public Boolean isVerifiedValidID() {
        return verifiedValidID;
    }

    public void setVerifiedValidID(Boolean verifiedValidID) {
        this.verifiedValidID = verifiedValidID;
    }

    public Boolean isEvidenceofAddress() {
        return evidenceofAddress;
    }

    public void setEvidenceofAddress(Boolean evidenceofAddress) {
        this.evidenceofAddress = evidenceofAddress;
    }

    public Boolean isVerificationofAddress() {
        return verificationofAddress;
    }

    public void setVerificationofAddress(Boolean verificationofAddress) {
        this.verificationofAddress = verificationofAddress;
    }

    public Boolean isEmploymentDetails() {
        return employmentDetails;
    }

    public void setEmploymentDetails(Boolean employmentDetails) {
        this.employmentDetails = employmentDetails;
    }

    public Double getDailyTransactionLimit() {
        return dailyTransactionLimit;
    }

    public void setDailyTransactionLimit(Double dailyTransactionLimit) {
        this.dailyTransactionLimit = dailyTransactionLimit;
    }

    public Double getCumulativeBalanceLimit() {
        return cumulativeBalanceLimit;
    }

    public void setCumulativeBalanceLimit(Double cumulativeBalanceLimit) {
        this.cumulativeBalanceLimit = cumulativeBalanceLimit;
    }

    public Boolean isPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(Boolean paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public Boolean isBillerTransaction() {
        return billerTransaction;
    }

    public void setBillerTransaction(Boolean billerTransaction) {
        this.billerTransaction = billerTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KyclevelDTO)) {
            return false;
        }

        return id != null && id.equals(((KyclevelDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KyclevelDTO{" +
            "id=" + getId() +
            ", kycID=" + getKycID() +
            ", kyc='" + getKyc() + "'" +
            ", description='" + getDescription() + "'" +
            ", kycLevel=" + getKycLevel() +
            ", phoneNumber='" + isPhoneNumber() + "'" +
            ", emailAddress='" + isEmailAddress() + "'" +
            ", firstName='" + isFirstName() + "'" +
            ", lastName='" + isLastName() + "'" +
            ", gender='" + isGender() + "'" +
            ", dateofBirth='" + isDateofBirth() + "'" +
            ", address='" + isAddress() + "'" +
            ", photoUpload='" + isPhotoUpload() + "'" +
            ", verifiedBVN='" + isVerifiedBVN() + "'" +
            ", verifiedValidID='" + isVerifiedValidID() + "'" +
            ", evidenceofAddress='" + isEvidenceofAddress() + "'" +
            ", verificationofAddress='" + isVerificationofAddress() + "'" +
            ", employmentDetails='" + isEmploymentDetails() + "'" +
            ", dailyTransactionLimit=" + getDailyTransactionLimit() +
            ", cumulativeBalanceLimit=" + getCumulativeBalanceLimit() +
            ", paymentTransaction='" + isPaymentTransaction() + "'" +
            ", billerTransaction='" + isBillerTransaction() + "'" +
            "}";
    }
}
