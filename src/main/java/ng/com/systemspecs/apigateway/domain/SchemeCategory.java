package ng.com.systemspecs.apigateway.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SchemeCategory.
 */
@Entity
@Table(name = "scheme_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SchemeCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "schemecategory_id")
    private Long schemecategoryID;

    @Column(name = "scheme_category")
    private String schemeCategory;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSchemecategoryID() {
        return schemecategoryID;
    }

    public SchemeCategory schemecategoryID(Long schemecategoryID) {
        this.schemecategoryID = schemecategoryID;
        return this;
    }

    public void setSchemecategoryID(Long schemecategoryID) {
        this.schemecategoryID = schemecategoryID;
    }

    public String getSchemeCategory() {
        return schemeCategory;
    }

    public SchemeCategory schemeCategory(String schemeCategory) {
        this.schemeCategory = schemeCategory;
        return this;
    }

    public void setSchemeCategory(String schemeCategory) {
        this.schemeCategory = schemeCategory;
    }

    public String getDescription() {
        return description;
    }

    public SchemeCategory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SchemeCategory)) {
            return false;
        }
        return id != null && id.equals(((SchemeCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SchemeCategory{" +
            "id=" + getId() +
            ", schemecategoryID=" + getSchemecategoryID() +
            ", schemeCategory='" + getSchemeCategory() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
