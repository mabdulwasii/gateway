package ng.com.systemspecs.apigateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A BillerPlatform.
 */
@Entity
@Table(name = "biller_platform")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillerPlatform implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "billerplatform_id")
    private Long billerplatformID;

    @Column(name = "biller_platform")
    private String billerPlatform;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JsonIgnoreProperties(value = "billerPlatforms", allowSetters = true)
    private Biller biller;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillerplatformID() {
        return billerplatformID;
    }

    public BillerPlatform billerplatformID(Long billerplatformID) {
        this.billerplatformID = billerplatformID;
        return this;
    }

    public void setBillerplatformID(Long billerplatformID) {
        this.billerplatformID = billerplatformID;
    }

    public String getBillerPlatform() {
        return billerPlatform;
    }

    public BillerPlatform billerPlatform(String billerPlatform) {
        this.billerPlatform = billerPlatform;
        return this;
    }

    public void setBillerPlatform(String billerPlatform) {
        this.billerPlatform = billerPlatform;
    }

    public Double getAmount() {
        return amount;
    }

    public BillerPlatform amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Biller getBiller() {
        return biller;
    }

    public BillerPlatform biller(Biller biller) {
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
        if (!(o instanceof BillerPlatform)) {
            return false;
        }
        return id != null && id.equals(((BillerPlatform) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillerPlatform{" +
            "id=" + getId() +
            ", billerplatformID=" + getBillerplatformID() +
            ", billerPlatform='" + getBillerPlatform() + "'" +
            ", amount=" + getAmount() +
            "}";
    }
}
