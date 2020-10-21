package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.Scheme} entity.
 */
public class SchemeDTO implements Serializable {
    
    private Long id;

    private Long schemeID;

    private String scheme;


    private Long schemeCategoryId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSchemeID() {
        return schemeID;
    }

    public void setSchemeID(Long schemeID) {
        this.schemeID = schemeID;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Long getSchemeCategoryId() {
        return schemeCategoryId;
    }

    public void setSchemeCategoryId(Long schemeCategoryId) {
        this.schemeCategoryId = schemeCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SchemeDTO)) {
            return false;
        }

        return id != null && id.equals(((SchemeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SchemeDTO{" +
            "id=" + getId() +
            ", schemeID=" + getSchemeID() +
            ", scheme='" + getScheme() + "'" +
            ", schemeCategoryId=" + getSchemeCategoryId() +
            "}";
    }
}
