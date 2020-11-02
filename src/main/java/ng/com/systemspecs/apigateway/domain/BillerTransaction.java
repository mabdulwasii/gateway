package ng.com.systemspecs.apigateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A BillerTransaction.
 */
@Entity
@Table(name = "biller_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillerTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "billertrans_id")
    private Long billertransID;

    @Column(name = "transaction_ref")
    private String transactionRef;

    @Column(name = "narration")
    private String narration;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JsonIgnoreProperties(value = "billerTransactions", allowSetters = true)
    private Profile phoneNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillertransID() {
        return billertransID;
    }

    public BillerTransaction billertransID(Long billertransID) {
        this.billertransID = billertransID;
        return this;
    }

    public void setBillertransID(Long billertransID) {
        this.billertransID = billertransID;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public BillerTransaction transactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
        return this;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public String getNarration() {
        return narration;
    }

    public BillerTransaction narration(String narration) {
        this.narration = narration;
        return this;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Double getAmount() {
        return amount;
    }

    public BillerTransaction amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Profile getPhoneNumber() {
        return phoneNumber;
    }

    public BillerTransaction phoneNumber(Profile profile) {
        this.phoneNumber = profile;
        return this;
    }

    public void setPhoneNumber(Profile profile) {
        this.phoneNumber = profile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillerTransaction)) {
            return false;
        }
        return id != null && id.equals(((BillerTransaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillerTransaction{" +
            "id=" + getId() +
            ", billertransID=" + getBillertransID() +
            ", transactionRef='" + getTransactionRef() + "'" +
            ", narration='" + getNarration() + "'" +
            ", amount=" + getAmount() +
            "}";
    }
}
