package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.BillerCategory} entity.
 */
public class BillerCategoryDTO implements Serializable {
    
    private Long id;

    private Long billercategoryID;

    private String billerCategory;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillercategoryID() {
        return billercategoryID;
    }

    public void setBillercategoryID(Long billercategoryID) {
        this.billercategoryID = billercategoryID;
    }

    public String getBillerCategory() {
        return billerCategory;
    }

    public void setBillerCategory(String billerCategory) {
        this.billerCategory = billerCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillerCategoryDTO)) {
            return false;
        }

        return id != null && id.equals(((BillerCategoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillerCategoryDTO{" +
            "id=" + getId() +
            ", billercategoryID=" + getBillercategoryID() +
            ", billerCategory='" + getBillerCategory() + "'" +
            "}";
    }
}
