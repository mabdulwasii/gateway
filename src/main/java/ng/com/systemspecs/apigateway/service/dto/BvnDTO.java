package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

public class BvnDTO implements Serializable {
	
	private String  accountNumber;
	private String  bankCode;
	private String  bvn;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBvn() {
		return bvn;
	}
	public void setBvn(String bvn) {
		this.bvn = bvn;
	}
	
	
	
}
