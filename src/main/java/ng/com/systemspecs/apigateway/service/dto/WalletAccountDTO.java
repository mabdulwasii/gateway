package ng.com.systemspecs.apigateway.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link ng.com.systemspecs.apigateway.domain.WalletAccount} entity.
 */
public class WalletAccountDTO implements Serializable {
    
    private Long id;

    private Long accountNumber;

    private Double currentBalance;

    private LocalDate dateOpened;


    private Long schemeId;

    private Long walletAccountTypeId;

    private Long accountOwnerId;

    private String accountOwnerPhoneNumber;
    
    private String accountName;
    
    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }

    public Long getWalletAccountTypeId() {
        return walletAccountTypeId;
    }

    public void setWalletAccountTypeId(Long walletAccountTypeId) {
        this.walletAccountTypeId = walletAccountTypeId;
    }

    public Long getAccountOwnerId() {
        return accountOwnerId;
    }

    public void setAccountOwnerId(Long profileId) {
        this.accountOwnerId = profileId;
    }

    public String getAccountOwnerPhoneNumber() {
        return accountOwnerPhoneNumber;
    }

    public void setAccountOwnerPhoneNumber(String profilePhoneNumber) {
        this.accountOwnerPhoneNumber = profilePhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletAccountDTO)) {
            return false;
        }

        return id != null && id.equals(((WalletAccountDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletAccountDTO{" +
            "id=" + getId() +
            ", accountNumber=" + getAccountNumber() +
            ", currentBalance=" + getCurrentBalance() +
            ", dateOpened='" + getDateOpened() + "'" +
            ", schemeId=" + getSchemeId() +
            ", walletAccountTypeId=" + getWalletAccountTypeId() +
            ", accountOwnerId=" + getAccountOwnerId() +
            ", accountOwnerPhoneNumber='" + getAccountOwnerPhoneNumber() + "'" +
            "}";
    }
}
