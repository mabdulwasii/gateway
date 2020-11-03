package ng.com.systemspecs.apigateway.service.dto;

public class VerifyBankAccountDTO {
	
	
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
	private String  bankCode;
	private String accountName;

}
