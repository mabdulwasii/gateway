package ng.com.systemspecs.apigateway.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import ng.com.systemspecs.apigateway.domain.enumeration.TransactionType;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.PaymentTransaction} entity.
 */
public class PaymentTransactionDTO implements Serializable {

    private Long id;

    private LocalDate transactionDate;
    private Long paymenttransID;

    private TransactionType transactionType;

    private String transactionRef;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String channel;

    @NotNull
    private String currency;

    @NotNull
    private String sourceAccount;

    @NotNull
    private String sourceAccountBankCode;

    private String sourceAccountName;

    @NotNull
    private String sourceNarration;

    @NotNull
    private String destinationAccount;

    @NotNull
    private String destinationAccountBankCode;

    private String destinationAccountName;

    @NotNull
    private String destinationNarration;


    private Long transactionOwnerId;

    private String transactionOwnerPhoneNumber;

    public PaymentTransactionDTO() {
    }

    public PaymentTransactionDTO(Long id, LocalDate transactionDate, Long paymenttransID, TransactionType transactionType, String transactionRef, @NotNull BigDecimal amount, @NotNull String channel, @NotNull String currency, @NotNull String sourceAccount, @NotNull String sourceAccountBankCode, String sourceAccountName, @NotNull String sourceNarration, @NotNull String destinationAccount, @NotNull String destinationAccountBankCode, String destinationAccountName, @NotNull String destinationNarration, Long transactionOwnerId, String transactionOwnerPhoneNumber) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.paymenttransID = paymenttransID;
        this.transactionType = transactionType;
        this.transactionRef = transactionRef;
        this.amount = amount;
        this.channel = channel;
        this.currency = currency;
        this.sourceAccount = sourceAccount;
        this.sourceAccountBankCode = sourceAccountBankCode;
        this.sourceAccountName = sourceAccountName;
        this.sourceNarration = sourceNarration;
        this.destinationAccount = destinationAccount;
        this.destinationAccountBankCode = destinationAccountBankCode;
        this.destinationAccountName = destinationAccountName;
        this.destinationNarration = destinationNarration;
        this.transactionOwnerId = transactionOwnerId;
        this.transactionOwnerPhoneNumber = transactionOwnerPhoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymenttransID() {
        return paymenttransID;
    }

    public void setPaymenttransID(Long paymenttransID) {
        this.paymenttransID = paymenttransID;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getSourceAccountBankCode() {
        return sourceAccountBankCode;
    }

    public void setSourceAccountBankCode(String sourceAccountBankCode) {
        this.sourceAccountBankCode = sourceAccountBankCode;
    }

    public String getSourceAccountName() {
        return sourceAccountName;
    }

    public void setSourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    public String getSourceNarration() {
        return sourceNarration;
    }

    public void setSourceNarration(String sourceNarration) {
        this.sourceNarration = sourceNarration;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public String getDestinationAccountBankCode() {
        return destinationAccountBankCode;
    }

    public void setDestinationAccountBankCode(String destinationAccountBankCode) {
        this.destinationAccountBankCode = destinationAccountBankCode;
    }

    public String getDestinationAccountName() {
        return destinationAccountName;
    }

    public void setDestinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
    }

    public String getDestinationNarration() {
        return destinationNarration;
    }

    public void setDestinationNarration(String destinationNarration) {
        this.destinationNarration = destinationNarration;
    }

    public Long getTransactionOwnerId() {
        return transactionOwnerId;
    }

    public void setTransactionOwnerId(Long profileId) {
        this.transactionOwnerId = profileId;
    }

    public String getTransactionOwnerPhoneNumber() {
        return transactionOwnerPhoneNumber;
    }

    public void setTransactionOwnerPhoneNumber(String profilePhoneNumber) {
        this.transactionOwnerPhoneNumber = profilePhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentTransactionDTO)) {
            return false;
        }

        return id != null && id.equals(((PaymentTransactionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentTransactionDTO{" +
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
            ", transactionOwnerId=" + getTransactionOwnerId() +
            ", transactionOwnerPhoneNumber='" + getTransactionOwnerPhoneNumber() + "'" +
            "}";
    }

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
}
