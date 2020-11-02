package ng.com.systemspecs.apigateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DoubleEntryLogger.
 */
@Entity
@Table(name = "double_entry_logger")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DoubleEntryLogger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_entered")
    private LocalDate dateEntered;

    @Column(name = "double_entry_code")
    private String doubleEntryCode;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "narration")
    private String narration;

    @ManyToOne
    @JsonIgnoreProperties(value = "doubleEntryLoggers", allowSetters = true)
    private CountrolAccount debit;

    @ManyToOne
    @JsonIgnoreProperties(value = "doubleEntryLoggers", allowSetters = true)
    private CountrolAccount credit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEntered() {
        return dateEntered;
    }

    public DoubleEntryLogger dateEntered(LocalDate dateEntered) {
        this.dateEntered = dateEntered;
        return this;
    }

    public void setDateEntered(LocalDate dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String getDoubleEntryCode() {
        return doubleEntryCode;
    }

    public DoubleEntryLogger doubleEntryCode(String doubleEntryCode) {
        this.doubleEntryCode = doubleEntryCode;
        return this;
    }

    public void setDoubleEntryCode(String doubleEntryCode) {
        this.doubleEntryCode = doubleEntryCode;
    }

    public Double getAmount() {
        return amount;
    }

    public DoubleEntryLogger amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNarration() {
        return narration;
    }

    public DoubleEntryLogger narration(String narration) {
        this.narration = narration;
        return this;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public CountrolAccount getDebit() {
        return debit;
    }

    public DoubleEntryLogger debit(CountrolAccount countrolAccount) {
        this.debit = countrolAccount;
        return this;
    }

    public void setDebit(CountrolAccount countrolAccount) {
        this.debit = countrolAccount;
    }

    public CountrolAccount getCredit() {
        return credit;
    }

    public DoubleEntryLogger credit(CountrolAccount countrolAccount) {
        this.credit = countrolAccount;
        return this;
    }

    public void setCredit(CountrolAccount countrolAccount) {
        this.credit = countrolAccount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoubleEntryLogger)) {
            return false;
        }
        return id != null && id.equals(((DoubleEntryLogger) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoubleEntryLogger{" +
            "id=" + getId() +
            ", dateEntered='" + getDateEntered() + "'" +
            ", doubleEntryCode='" + getDoubleEntryCode() + "'" +
            ", amount=" + getAmount() +
            ", narration='" + getNarration() + "'" +
            "}";
    }
}
