package ng.com.systemspecs.apigateway.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * This setup different KYC Level a Customer can be in
 */
@Entity
@Table(name = "kyclevel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Kyclevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "kyc_id")
    private Long kycID;

    @Column(name = "kyc")
    private String kyc;

    @Column(name = "description")
    private String description;

    @Column(name = "kyc_level")
    private Integer kycLevel;

    @Column(name = "phone_number")
    private Boolean phoneNumber;

    @Column(name = "email_address")
    private Boolean emailAddress;

    @Column(name = "first_name")
    private Boolean firstName;

    @Column(name = "last_name")
    private Boolean lastName;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "dateof_birth")
    private Boolean dateofBirth;

    @Column(name = "address")
    private Boolean address;

    @Column(name = "photo_upload")
    private Boolean photoUpload;

    @Column(name = "verified_bvn")
    private Boolean verifiedBVN;

    @Column(name = "verified_valid_id")
    private Boolean verifiedValidID;

    @Column(name = "evidenceof_address")
    private Boolean evidenceofAddress;

    @Column(name = "verificationof_address")
    private Boolean verificationofAddress;

    @Column(name = "employment_details")
    private Boolean employmentDetails;

    @Column(name = "daily_transaction_limit")
    private Double dailyTransactionLimit;

    @Column(name = "cumulative_balance_limit")
    private Double cumulativeBalanceLimit;

    @Column(name = "payment_transaction")
    private Boolean paymentTransaction;

    @Column(name = "biller_transaction")
    private Boolean billerTransaction;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKycID() {
        return kycID;
    }

    public Kyclevel kycID(Long kycID) {
        this.kycID = kycID;
        return this;
    }

    public void setKycID(Long kycID) {
        this.kycID = kycID;
    }

    public String getKyc() {
        return kyc;
    }

    public Kyclevel kyc(String kyc) {
        this.kyc = kyc;
        return this;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
    }

    public String getDescription() {
        return description;
    }

    public Kyclevel description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getKycLevel() {
        return kycLevel;
    }

    public Kyclevel kycLevel(Integer kycLevel) {
        this.kycLevel = kycLevel;
        return this;
    }

    public void setKycLevel(Integer kycLevel) {
        this.kycLevel = kycLevel;
    }

    public Boolean isPhoneNumber() {
        return phoneNumber;
    }

    public Kyclevel phoneNumber(Boolean phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Boolean phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean isEmailAddress() {
        return emailAddress;
    }

    public Kyclevel emailAddress(Boolean emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(Boolean emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean isFirstName() {
        return firstName;
    }

    public Kyclevel firstName(Boolean firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(Boolean firstName) {
        this.firstName = firstName;
    }

    public Boolean isLastName() {
        return lastName;
    }

    public Kyclevel lastName(Boolean lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(Boolean lastName) {
        this.lastName = lastName;
    }

    public Boolean isGender() {
        return gender;
    }

    public Kyclevel gender(Boolean gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean isDateofBirth() {
        return dateofBirth;
    }

    public Kyclevel dateofBirth(Boolean dateofBirth) {
        this.dateofBirth = dateofBirth;
        return this;
    }

    public void setDateofBirth(Boolean dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public Boolean isAddress() {
        return address;
    }

    public Kyclevel address(Boolean address) {
        this.address = address;
        return this;
    }

    public void setAddress(Boolean address) {
        this.address = address;
    }

    public Boolean isPhotoUpload() {
        return photoUpload;
    }

    public Kyclevel photoUpload(Boolean photoUpload) {
        this.photoUpload = photoUpload;
        return this;
    }

    public void setPhotoUpload(Boolean photoUpload) {
        this.photoUpload = photoUpload;
    }

    public Boolean isVerifiedBVN() {
        return verifiedBVN;
    }

    public Kyclevel verifiedBVN(Boolean verifiedBVN) {
        this.verifiedBVN = verifiedBVN;
        return this;
    }

    public void setVerifiedBVN(Boolean verifiedBVN) {
        this.verifiedBVN = verifiedBVN;
    }

    public Boolean isVerifiedValidID() {
        return verifiedValidID;
    }

    public Kyclevel verifiedValidID(Boolean verifiedValidID) {
        this.verifiedValidID = verifiedValidID;
        return this;
    }

    public void setVerifiedValidID(Boolean verifiedValidID) {
        this.verifiedValidID = verifiedValidID;
    }

    public Boolean isEvidenceofAddress() {
        return evidenceofAddress;
    }

    public Kyclevel evidenceofAddress(Boolean evidenceofAddress) {
        this.evidenceofAddress = evidenceofAddress;
        return this;
    }

    public void setEvidenceofAddress(Boolean evidenceofAddress) {
        this.evidenceofAddress = evidenceofAddress;
    }

    public Boolean isVerificationofAddress() {
        return verificationofAddress;
    }

    public Kyclevel verificationofAddress(Boolean verificationofAddress) {
        this.verificationofAddress = verificationofAddress;
        return this;
    }

    public void setVerificationofAddress(Boolean verificationofAddress) {
        this.verificationofAddress = verificationofAddress;
    }

    public Boolean isEmploymentDetails() {
        return employmentDetails;
    }

    public Kyclevel employmentDetails(Boolean employmentDetails) {
        this.employmentDetails = employmentDetails;
        return this;
    }

    public void setEmploymentDetails(Boolean employmentDetails) {
        this.employmentDetails = employmentDetails;
    }

    public Double getDailyTransactionLimit() {
        return dailyTransactionLimit;
    }

    public Kyclevel dailyTransactionLimit(Double dailyTransactionLimit) {
        this.dailyTransactionLimit = dailyTransactionLimit;
        return this;
    }

    public void setDailyTransactionLimit(Double dailyTransactionLimit) {
        this.dailyTransactionLimit = dailyTransactionLimit;
    }

    public Double getCumulativeBalanceLimit() {
        return cumulativeBalanceLimit;
    }

    public Kyclevel cumulativeBalanceLimit(Double cumulativeBalanceLimit) {
        this.cumulativeBalanceLimit = cumulativeBalanceLimit;
        return this;
    }

    public void setCumulativeBalanceLimit(Double cumulativeBalanceLimit) {
        this.cumulativeBalanceLimit = cumulativeBalanceLimit;
    }

    public Boolean isPaymentTransaction() {
        return paymentTransaction;
    }

    public Kyclevel paymentTransaction(Boolean paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
        return this;
    }

    public void setPaymentTransaction(Boolean paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public Boolean isBillerTransaction() {
        return billerTransaction;
    }

    public Kyclevel billerTransaction(Boolean billerTransaction) {
        this.billerTransaction = billerTransaction;
        return this;
    }

    public void setBillerTransaction(Boolean billerTransaction) {
        this.billerTransaction = billerTransaction;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Kyclevel)) {
            return false;
        }
        return id != null && id.equals(((Kyclevel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Kyclevel{" +
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
