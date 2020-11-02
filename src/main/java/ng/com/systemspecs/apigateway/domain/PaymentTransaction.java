package ng.com.systemspecs.apigateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

import ng.com.systemspecs.apigateway.domain.enumeration.TransactionType;

/**
 * A PaymentTransaction.
 */
@Entity
@Table(name = "payment_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "paymenttrans_id")
    private Long paymenttransID;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_ref")
    private String transactionRef;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "channel", nullable = false)
    private String channel;

    @NotNull
    @Column(name = "currency", nullable = false)
    private String currency;

    @NotNull
    @Column(name = "source_account", nullable = false)
    private String sourceAccount;

    @NotNull
    @Column(name = "source_account_bank_code", nullable = false)
    private String sourceAccountBankCode;

    @Column(name = "source_account_name")
    private String sourceAccountName;

    @NotNull
    @Column(name = "source_narration", nullable = false)
    private String sourceNarration;

    @NotNull
    @Column(name = "destination_account", nullable = false)
    private String destinationAccount;

    @NotNull
    @Column(name = "destination_account_bank_code", nullable = false)
    private String destinationAccountBankCode;

    @Column(name = "destination_account_name")
    private String destinationAccountName;

    @NotNull
    @Column(name = "destination_narration", nullable = false)
    private String destinationNarration;

    @ManyToOne
    @JsonIgnoreProperties(value = "paymentTransactions", allowSetters = true)
    private Profile transactionOwner;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymenttransID() {
        return paymenttransID;
    }

    public PaymentTransaction paymenttransID(Long paymenttransID) {
        this.paymenttransID = paymenttransID;
        return this;
    }

    public void setPaymenttransID(Long paymenttransID) {
        this.paymenttransID = paymenttransID;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public PaymentTransaction transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public PaymentTransaction transactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
        return this;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentTransaction amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getChannel() {
        return channel;
    }

    public PaymentTransaction channel(String channel) {
        this.channel = channel;
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCurrency() {
        return currency;
    }

    public PaymentTransaction currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public PaymentTransaction sourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
        return this;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getSourceAccountBankCode() {
        return sourceAccountBankCode;
    }

    public PaymentTransaction sourceAccountBankCode(String sourceAccountBankCode) {
        this.sourceAccountBankCode = sourceAccountBankCode;
        return this;
    }

    public void setSourceAccountBankCode(String sourceAccountBankCode) {
        this.sourceAccountBankCode = sourceAccountBankCode;
    }

    public String getSourceAccountName() {
        return sourceAccountName;
    }

    public PaymentTransaction sourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
        return this;
    }

    public void setSourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    public String getSourceNarration() {
        return sourceNarration;
    }

    public PaymentTransaction sourceNarration(String sourceNarration) {
        this.sourceNarration = sourceNarration;
        return this;
    }

    public void setSourceNarration(String sourceNarration) {
        this.sourceNarration = sourceNarration;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public PaymentTransaction destinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
        return this;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public String getDestinationAccountBankCode() {
        return destinationAccountBankCode;
    }

    public PaymentTransaction destinationAccountBankCode(String destinationAccountBankCode) {
        this.destinationAccountBankCode = destinationAccountBankCode;
        return this;
    }

    public void setDestinationAccountBankCode(String destinationAccountBankCode) {
        this.destinationAccountBankCode = destinationAccountBankCode;
    }

    public String getDestinationAccountName() {
        return destinationAccountName;
    }

    public PaymentTransaction destinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
        return this;
    }

    public void setDestinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
    }

    public String getDestinationNarration() {
        return destinationNarration;
    }

    public PaymentTransaction destinationNarration(String destinationNarration) {
        this.destinationNarration = destinationNarration;
        return this;
    }

    public void setDestinationNarration(String destinationNarration) {
        this.destinationNarration = destinationNarration;
    }

    public Profile getTransactionOwner() {
        return transactionOwner;
    }

    public PaymentTransaction transactionOwner(Profile profile) {
        this.transactionOwner = profile;
        return this;
    }

    public void setTransactionOwner(Profile profile) {
        this.transactionOwner = profile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentTransaction)) {
            return false;
        }
        return id != null && id.equals(((PaymentTransaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentTransaction{" +
            "id=" + getId() +
            ", paymenttransID=" + getPaymenttransID() +
            ", transactionType='" + getTransactionType() + "'" +
            ", transactionRef='" + getTransactionRef() + "'" +
            ", amount=" + getAmount() +
            ", channel='" + getChannel() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", sourceAccount='" + getSourceAccount() + "'" +
            ", sourceAccountBankCode='" + getSourceAccountBankCode() + "'" +
            ", sourceAccountName='" + getSourceAccountName() + "'" +
            ", sourceNarration='" + getSourceNarration() + "'" +
            ", destinationAccount='" + getDestinationAccount() + "'" +
            ", destinationAccountBankCode='" + getDestinationAccountBankCode() + "'" +
            ", destinationAccountName='" + getDestinationAccountName() + "'" +
            ", destinationNarration='" + getDestinationNarration() + "'" +
            "}";
    }
}
