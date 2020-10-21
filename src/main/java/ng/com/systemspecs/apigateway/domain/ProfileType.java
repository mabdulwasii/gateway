package ng.com.systemspecs.apigateway.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * This defines different Profile Type - Customer, Agent or Admin
 */
@Entity
@Table(name = "profile_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProfileType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "profiletype_id")
    private Long profiletypeID;

    @Column(name = "profiletype")
    private String profiletype;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfiletypeID() {
        return profiletypeID;
    }

    public ProfileType profiletypeID(Long profiletypeID) {
        this.profiletypeID = profiletypeID;
        return this;
    }

    public void setProfiletypeID(Long profiletypeID) {
        this.profiletypeID = profiletypeID;
    }

    public String getProfiletype() {
        return profiletype;
    }

    public ProfileType profiletype(String profiletype) {
        this.profiletype = profiletype;
        return this;
    }

    public void setProfiletype(String profiletype) {
        this.profiletype = profiletype;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileType)) {
            return false;
        }
        return id != null && id.equals(((ProfileType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileType{" +
            "id=" + getId() +
            ", profiletypeID=" + getProfiletypeID() +
            ", profiletype='" + getProfiletype() + "'" +
            "}";
    }
}
