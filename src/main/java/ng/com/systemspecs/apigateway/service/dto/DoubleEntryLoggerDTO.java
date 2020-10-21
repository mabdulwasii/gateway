package ng.com.systemspecs.apigateway.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.DoubleEntryLogger} entity.
 */
public class DoubleEntryLoggerDTO implements Serializable {
    
    private Long id;

    private LocalDate dateEntered;

    private String doubleEntryCode;

    private Double amount;

    private String narration;


    private Long debitId;

    private String debitCountrolAccountCode;

    private Long creditId;

    private String creditCountrolAccountCode;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(LocalDate dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String getDoubleEntryCode() {
        return doubleEntryCode;
    }

    public void setDoubleEntryCode(String doubleEntryCode) {
        this.doubleEntryCode = doubleEntryCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Long getDebitId() {
        return debitId;
    }

    public void setDebitId(Long countrolAccountId) {
        this.debitId = countrolAccountId;
    }

    public String getDebitCountrolAccountCode() {
        return debitCountrolAccountCode;
    }

    public void setDebitCountrolAccountCode(String countrolAccountCountrolAccountCode) {
        this.debitCountrolAccountCode = countrolAccountCountrolAccountCode;
    }

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long countrolAccountId) {
        this.creditId = countrolAccountId;
    }

    public String getCreditCountrolAccountCode() {
        return creditCountrolAccountCode;
    }

    public void setCreditCountrolAccountCode(String countrolAccountCountrolAccountCode) {
        this.creditCountrolAccountCode = countrolAccountCountrolAccountCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoubleEntryLoggerDTO)) {
            return false;
        }

        return id != null && id.equals(((DoubleEntryLoggerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoubleEntryLoggerDTO{" +
            "id=" + getId() +
            ", dateEntered='" + getDateEntered() + "'" +
            ", doubleEntryCode='" + getDoubleEntryCode() + "'" +
            ", amount=" + getAmount() +
            ", narration='" + getNarration() + "'" +
            ", debitId=" + getDebitId() +
            ", debitCountrolAccountCode='" + getDebitCountrolAccountCode() + "'" +
            ", creditId=" + getCreditId() +
            ", creditCountrolAccountCode='" + getCreditCountrolAccountCode() + "'" +
            "}";
    }
}
