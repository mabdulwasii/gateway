package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.BillerTransaction} entity.
 */
public class BillerTransactionDTO implements Serializable {
    
    private Long id;

    private Long billertransID;

    private String transactionRef;

    private String narration;

    private Double amount;


    private Long phoneNumberId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillertransID() {
        return billertransID;
    }

    public void setBillertransID(Long billertransID) {
        this.billertransID = billertransID;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getPhoneNumberId() {
        return phoneNumberId;
    }

    public void setPhoneNumberId(Long profileId) {
        this.phoneNumberId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillerTransactionDTO)) {
            return false;
        }

        return id != null && id.equals(((BillerTransactionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillerTransactionDTO{" +
            "id=" + getId() +
            ", billertransID=" + getBillertransID() +
            ", transactionRef='" + getTransactionRef() + "'" +
            ", narration='" + getNarration() + "'" +
            ", amount=" + getAmount() +
            ", phoneNumberId=" + getPhoneNumberId() +
            "}";
    }
}
