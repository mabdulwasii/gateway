package ng.com.systemspecs.apigateway.service.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ng.com.systemspecs.apigateway.domain.enumeration.AccountOperation;

public class JounalLineDTO {
	private Long accountNumber;
	
    @Enumerated(EnumType.STRING)
	private AccountOperation operation;
    
	private Double amount;
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public AccountOperation getOperation() {
		return operation;
	}
	public void setOperation(AccountOperation operation) {
		this.operation = operation;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
