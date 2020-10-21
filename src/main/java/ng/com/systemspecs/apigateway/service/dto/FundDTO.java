package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

public class FundDTO implements Serializable {

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	private String accountNumber;
	private Double amount;
}
