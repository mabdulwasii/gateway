package ng.com.systemspecs.apigateway.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A BillerCategory.
 */
@Entity
@Table(name = "biller_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillerCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "billercategory_id")
    private Long billercategoryID;

    @Column(name = "biller_category")
    private String billerCategory;

    @OneToMany(mappedBy = "billerCategory")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Biller> billers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillercategoryID() {
        return billercategoryID;
    }

    public BillerCategory billercategoryID(Long billercategoryID) {
        this.billercategoryID = billercategoryID;
        return this;
    }

    public void setBillercategoryID(Long billercategoryID) {
        this.billercategoryID = billercategoryID;
    }

    public String getBillerCategory() {
        return billerCategory;
    }

    public BillerCategory billerCategory(String billerCategory) {
        this.billerCategory = billerCategory;
        return this;
    }

    public void setBillerCategory(String billerCategory) {
        this.billerCategory = billerCategory;
    }

    public Set<Biller> getBillers() {
        return billers;
    }

    public BillerCategory billers(Set<Biller> billers) {
        this.billers = billers;
        return this;
    }

    public BillerCategory addBiller(Biller biller) {
        this.billers.add(biller);
        biller.setBillerCategory(this);
        return this;
    }

    public BillerCategory removeBiller(Biller biller) {
        this.billers.remove(biller);
        biller.setBillerCategory(null);
        return this;
    }

    public void setBillers(Set<Biller> billers) {
        this.billers = billers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillerCategory)) {
            return false;
        }
        return id != null && id.equals(((BillerCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillerCategory{" +
            "id=" + getId() +
            ", billercategoryID=" + getBillercategoryID() +
            ", billerCategory='" + getBillerCategory() + "'" +
            "}";
    }
}
