package ng.com.systemspecs.apigateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Scheme.
 */
@Entity
@Table(name = "scheme")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Scheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "scheme_id")
    private Long schemeID;

    @Column(name = "scheme")
    private String scheme;

    @ManyToOne
    @JsonIgnoreProperties(value = "schemes", allowSetters = true)
    private SchemeCategory schemeCategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSchemeID() {
        return schemeID;
    }

    public Scheme schemeID(Long schemeID) {
        this.schemeID = schemeID;
        return this;
    }

    public void setSchemeID(Long schemeID) {
        this.schemeID = schemeID;
    }

    public String getScheme() {
        return scheme;
    }

    public Scheme scheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public SchemeCategory getSchemeCategory() {
        return schemeCategory;
    }

    public Scheme schemeCategory(SchemeCategory schemeCategory) {
        this.schemeCategory = schemeCategory;
        return this;
    }

    public void setSchemeCategory(SchemeCategory schemeCategory) {
        this.schemeCategory = schemeCategory;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Scheme)) {
            return false;
        }
        return id != null && id.equals(((Scheme) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Scheme{" +
            "id=" + getId() +
            ", schemeID=" + getSchemeID() +
            ", scheme='" + getScheme() + "'" +
            "}";
    }
}
