package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

public class SendMoneyDTO implements Serializable {
	
	 
	
	public String getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public String getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getSourceBankCode() {
		return sourceBankCode;
	}
	public void setSourceBankCode(String sourceBankCode) {
		this.sourceBankCode = sourceBankCode;
	}
	public String getDestinationBankCode() {
		return destinationBankCode;
	}
	public void setDestinationBankCode(String destinationBankCode) {
		this.destinationBankCode = destinationBankCode;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	
	private String sourceAccount;
	private String destinationAccount;
	private Double amount;
	private String sourceBankCode;
	private String destinationBankCode;
	private String  channel;
	private String  pin;

}
