package ng.com.systemspecs.apigateway.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CountrolAccount.
 */
@Entity
@Table(name = "countrol_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CountrolAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "countrol_account_code")
    private String countrolAccountCode;

    @Column(name = "countrol_account_name")
    private String countrolAccountName;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountrolAccountCode() {
        return countrolAccountCode;
    }

    public CountrolAccount countrolAccountCode(String countrolAccountCode) {
        this.countrolAccountCode = countrolAccountCode;
        return this;
    }

    public void setCountrolAccountCode(String countrolAccountCode) {
        this.countrolAccountCode = countrolAccountCode;
    }

    public String getCountrolAccountName() {
        return countrolAccountName;
    }

    public CountrolAccount countrolAccountName(String countrolAccountName) {
        this.countrolAccountName = countrolAccountName;
        return this;
    }

    public void setCountrolAccountName(String countrolAccountName) {
        this.countrolAccountName = countrolAccountName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CountrolAccount)) {
            return false;
        }
        return id != null && id.equals(((CountrolAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CountrolAccount{" +
            "id=" + getId() +
            ", countrolAccountCode='" + getCountrolAccountCode() + "'" +
            ", countrolAccountName='" + getCountrolAccountName() + "'" +
            "}";
    }
}
