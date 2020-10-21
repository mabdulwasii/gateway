package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.WalletAccountType} entity.
 */
public class WalletAccountTypeDTO implements Serializable {
    
    private Long id;

    private Long accountypeID;

    private String walletAccountType;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountypeID() {
        return accountypeID;
    }

    public void setAccountypeID(Long accountypeID) {
        this.accountypeID = accountypeID;
    }

    public String getWalletAccountType() {
        return walletAccountType;
    }

    public void setWalletAccountType(String walletAccountType) {
        this.walletAccountType = walletAccountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletAccountTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((WalletAccountTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletAccountTypeDTO{" +
            "id=" + getId() +
            ", accountypeID=" + getAccountypeID() +
            ", walletAccountType='" + getWalletAccountType() + "'" +
            "}";
    }
}
