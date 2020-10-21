package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.BillerPlatform} entity.
 */
public class BillerPlatformDTO implements Serializable {
    
    private Long id;

    private Long billerplatformID;

    private String billerPlatform;

    private Double amount;


    private Long billerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillerplatformID() {
        return billerplatformID;
    }

    public void setBillerplatformID(Long billerplatformID) {
        this.billerplatformID = billerplatformID;
    }

    public String getBillerPlatform() {
        return billerPlatform;
    }

    public void setBillerPlatform(String billerPlatform) {
        this.billerPlatform = billerPlatform;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getBillerId() {
        return billerId;
    }

    public void setBillerId(Long billerId) {
        this.billerId = billerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillerPlatformDTO)) {
            return false;
        }

        return id != null && id.equals(((BillerPlatformDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillerPlatformDTO{" +
            "id=" + getId() +
            ", billerplatformID=" + getBillerplatformID() +
            ", billerPlatform='" + getBillerPlatform() + "'" +
            ", amount=" + getAmount() +
            ", billerId=" + getBillerId() +
            "}";
    }
}
