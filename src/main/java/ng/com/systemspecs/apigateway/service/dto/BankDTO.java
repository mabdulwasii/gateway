package ng.com.systemspecs.apigateway.service.dto;

public class BankDTO {
	
	
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
	private String  bankCode;
	private String  bankName;

}
