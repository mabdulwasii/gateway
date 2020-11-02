package ng.com.systemspecs.apigateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A WalletAccount.
 */
@Entity
@Table(name = "wallet_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WalletAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "account_name")
    private String accountName;   
    
    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "current_balance")
    private Double currentBalance;

    @Column(name = "date_opened")
    private LocalDate dateOpened;

    @ManyToOne
    @JsonIgnoreProperties(value = "walletAccounts", allowSetters = true)
    private Scheme scheme;

    @ManyToOne
    @JsonIgnoreProperties(value = "walletAccounts", allowSetters = true)
    private WalletAccountType walletAccountType;

    @ManyToOne
    @JsonIgnoreProperties(value = "walletAccounts", allowSetters = true)
    private Profile accountOwner;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public WalletAccount accountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public WalletAccount currentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
        return this;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public WalletAccount dateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
        return this;
    }

    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public WalletAccount scheme(Scheme scheme) {
        this.scheme = scheme;
        return this;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public WalletAccountType getWalletAccountType() {
        return walletAccountType;
    }

    public WalletAccount walletAccountType(WalletAccountType walletAccountType) {
        this.walletAccountType = walletAccountType;
        return this;
    }

    public void setWalletAccountType(WalletAccountType walletAccountType) {
        this.walletAccountType = walletAccountType;
    }

    public Profile getAccountOwner() {
        return accountOwner;
    }

    public WalletAccount accountOwner(Profile profile) {
        this.accountOwner = profile;
        return this;
    }

    public void setAccountOwner(Profile profile) {
        this.accountOwner = profile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletAccount)) {
            return false;
        }
        return id != null && id.equals(((WalletAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletAccount{" +
            "id=" + getId() +
            ", accountNumber=" + getAccountNumber() +
            ", currentBalance=" + getCurrentBalance() +
            ", dateOpened='" + getDateOpened() + "'" +
            "}";
    }
}
