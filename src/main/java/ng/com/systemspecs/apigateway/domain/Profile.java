package ng.com.systemspecs.apigateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import ng.com.systemspecs.apigateway.domain.enumeration.Gender;

/**
 * Profile of the user of the Solution this can be of any of the types :- Customer, Agent or Admin
 */
@Entity
@Table(name = "profile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "profile_id")
    private String profileID;
    
    @Column(name = "xxxx")
    private Integer pin;

    public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	@Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Column(name = "bvn")
    private String bvn;

    @Column(name = "valid_id")
    private String validID;

    @OneToMany(mappedBy = "accountOwner")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<WalletAccount> walletAccounts = new HashSet<>();

    @OneToMany(mappedBy = "transactionOwner")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PaymentTransaction> paymentTransactions = new HashSet<>();

    @OneToMany(mappedBy = "phoneNumber")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<BillerTransaction> billerTransactions = new HashSet<>();

    @OneToMany(mappedBy = "phoneNumber")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Customersubscription> customersubscriptions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "profiles", allowSetters = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = "profiles", allowSetters = true)
    private ProfileType profileType;

    @ManyToOne
    @JsonIgnoreProperties(value = "profiles", allowSetters = true)
    private Kyclevel kyc;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileID() {
        return profileID;
    }

    public Profile profileID(String profileID) {
        this.profileID = profileID;
        return this;
    }

    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Profile phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public Profile gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Profile dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public Profile address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Profile photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public Profile photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getBvn() {
        return bvn;
    }

    public Profile bvn(String bvn) {
        this.bvn = bvn;
        return this;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getValidID() {
        return validID;
    }

    public Profile validID(String validID) {
        this.validID = validID;
        return this;
    }

    public void setValidID(String validID) {
        this.validID = validID;
    }

    public Set<WalletAccount> getWalletAccounts() {
        return walletAccounts;
    }

    public Profile walletAccounts(Set<WalletAccount> walletAccounts) {
        this.walletAccounts = walletAccounts;
        return this;
    }

    public Profile addWalletAccount(WalletAccount walletAccount) {
        this.walletAccounts.add(walletAccount);
        walletAccount.setAccountOwner(this);
        return this;
    }

    public Profile removeWalletAccount(WalletAccount walletAccount) {
        this.walletAccounts.remove(walletAccount);
        walletAccount.setAccountOwner(null);
        return this;
    }

    public void setWalletAccounts(Set<WalletAccount> walletAccounts) {
        this.walletAccounts = walletAccounts;
    }

    public Set<PaymentTransaction> getPaymentTransactions() {
        return paymentTransactions;
    }

    public Profile paymentTransactions(Set<PaymentTransaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
        return this;
    }

    public Profile addPaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransactions.add(paymentTransaction);
        paymentTransaction.setTransactionOwner(this);
        return this;
    }

    public Profile removePaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransactions.remove(paymentTransaction);
        paymentTransaction.setTransactionOwner(null);
        return this;
    }

    public void setPaymentTransactions(Set<PaymentTransaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
    }

    public Set<BillerTransaction> getBillerTransactions() {
        return billerTransactions;
    }

    public Profile billerTransactions(Set<BillerTransaction> billerTransactions) {
        this.billerTransactions = billerTransactions;
        return this;
    }

    public Profile addBillerTransaction(BillerTransaction billerTransaction) {
        this.billerTransactions.add(billerTransaction);
        billerTransaction.setPhoneNumber(this);
        return this;
    }

    public Profile removeBillerTransaction(BillerTransaction billerTransaction) {
        this.billerTransactions.remove(billerTransaction);
        billerTransaction.setPhoneNumber(null);
        return this;
    }

    public void setBillerTransactions(Set<BillerTransaction> billerTransactions) {
        this.billerTransactions = billerTransactions;
    }

    public Set<Customersubscription> getCustomersubscriptions() {
        return customersubscriptions;
    }

    public Profile customersubscriptions(Set<Customersubscription> customersubscriptions) {
        this.customersubscriptions = customersubscriptions;
        return this;
    }

    public Profile addCustomersubscription(Customersubscription customersubscription) {
        this.customersubscriptions.add(customersubscription);
        customersubscription.setPhoneNumber(this);
        return this;
    }

    public Profile removeCustomersubscription(Customersubscription customersubscription) {
        this.customersubscriptions.remove(customersubscription);
        customersubscription.setPhoneNumber(null);
        return this;
    }

    public void setCustomersubscriptions(Set<Customersubscription> customersubscriptions) {
        this.customersubscriptions = customersubscriptions;
    }

    public User getUser() {
        return user;
    }

    public Profile user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public Profile profileType(ProfileType profileType) {
        this.profileType = profileType;
        return this;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }

    public Kyclevel getKyc() {
        return kyc;
    }

    public Profile kyc(Kyclevel kyclevel) {
        this.kyc = kyclevel;
        return this;
    }

    public void setKyc(Kyclevel kyclevel) {
        this.kyc = kyclevel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profile)) {
            return false;
        }
        return id != null && id.equals(((Profile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Profile{" +
            "id=" + getId() +
            ", profileID='" + getProfileID() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", gender='" + getGender() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", address='" + getAddress() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + getPhotoContentType() + "'" +
            ", bvn='" + getBvn() + "'" +
            ", validID='" + getValidID() + "'" +
            "}";
    }
}
