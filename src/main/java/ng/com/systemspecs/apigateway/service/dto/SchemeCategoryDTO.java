package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.SchemeCategory} entity.
 */
public class SchemeCategoryDTO implements Serializable {
    
    private Long id;

    private Long schemecategoryID;

    private String schemeCategory;

    private String description;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSchemecategoryID() {
        return schemecategoryID;
    }

    public void setSchemecategoryID(Long schemecategoryID) {
        this.schemecategoryID = schemecategoryID;
    }

    public String getSchemeCategory() {
        return schemeCategory;
    }

    public void setSchemeCategory(String schemeCategory) {
        this.schemeCategory = schemeCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SchemeCategoryDTO)) {
            return false;
        }

        return id != null && id.equals(((SchemeCategoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SchemeCategoryDTO{" +
            "id=" + getId() +
            ", schemecategoryID=" + getSchemecategoryID() +
            ", schemeCategory='" + getSchemeCategory() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
