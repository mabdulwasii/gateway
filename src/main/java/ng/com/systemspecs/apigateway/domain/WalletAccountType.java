package ng.com.systemspecs.apigateway.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A WalletAccountType.
 */
@Entity
@Table(name = "wallet_account_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WalletAccountType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "accountype_id")
    private Long accountypeID;

    @Column(name = "wallet_account_type")
    private String walletAccountType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountypeID() {
        return accountypeID;
    }

    public WalletAccountType accountypeID(Long accountypeID) {
        this.accountypeID = accountypeID;
        return this;
    }

    public void setAccountypeID(Long accountypeID) {
        this.accountypeID = accountypeID;
    }

    public String getWalletAccountType() {
        return walletAccountType;
    }

    public WalletAccountType walletAccountType(String walletAccountType) {
        this.walletAccountType = walletAccountType;
        return this;
    }

    public void setWalletAccountType(String walletAccountType) {
        this.walletAccountType = walletAccountType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletAccountType)) {
            return false;
        }
        return id != null && id.equals(((WalletAccountType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletAccountType{" +
            "id=" + getId() +
            ", accountypeID=" + getAccountypeID() +
            ", walletAccountType='" + getWalletAccountType() + "'" +
            "}";
    }
}
