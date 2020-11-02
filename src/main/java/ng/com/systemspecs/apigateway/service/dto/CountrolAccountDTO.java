package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.CountrolAccount} entity.
 */
public class CountrolAccountDTO implements Serializable {
    
    private Long id;

    private String countrolAccountCode;

    private String countrolAccountName;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountrolAccountCode() {
        return countrolAccountCode;
    }

    public void setCountrolAccountCode(String countrolAccountCode) {
        this.countrolAccountCode = countrolAccountCode;
    }

    public String getCountrolAccountName() {
        return countrolAccountName;
    }

    public void setCountrolAccountName(String countrolAccountName) {
        this.countrolAccountName = countrolAccountName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CountrolAccountDTO)) {
            return false;
        }

        return id != null && id.equals(((CountrolAccountDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CountrolAccountDTO{" +
            "id=" + getId() +
            ", countrolAccountCode='" + getCountrolAccountCode() + "'" +
            ", countrolAccountName='" + getCountrolAccountName() + "'" +
            "}";
    }
}
