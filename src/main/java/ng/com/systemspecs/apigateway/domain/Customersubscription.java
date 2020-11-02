package ng.com.systemspecs.apigateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import ng.com.systemspecs.apigateway.domain.enumeration.Frequency;

/**
 * A Customersubscription.
 */
@Entity
@Table(name = "customersubscription")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customersubscription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "unique_id")
    private String uniqueID;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency")
    private Frequency frequency;

    @ManyToOne
    @JsonIgnoreProperties(value = "customersubscriptions", allowSetters = true)
    private Profile phoneNumber;

    @ManyToOne
    @JsonIgnoreProperties(value = "customersubscriptions", allowSetters = true)
    private Biller biller;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public Customersubscription uniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
        return this;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Customersubscription frequency(Frequency frequency) {
        this.frequency = frequency;
        return this;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Profile getPhoneNumber() {
        return phoneNumber;
    }

    public Customersubscription phoneNumber(Profile profile) {
        this.phoneNumber = profile;
        return this;
    }

    public void setPhoneNumber(Profile profile) {
        this.phoneNumber = profile;
    }

    public Biller getBiller() {
        return biller;
    }

    public Customersubscription biller(Biller biller) {
        this.biller = biller;
        return this;
    }

    public void setBiller(Biller biller) {
        this.biller = biller;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customersubscription)) {
            return false;
        }
        return id != null && id.equals(((Customersubscription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customersubscription{" +
            "id=" + getId() +
            ", uniqueID='" + getUniqueID() + "'" +
            ", frequency='" + getFrequency() + "'" +
            "}";
    }
}
