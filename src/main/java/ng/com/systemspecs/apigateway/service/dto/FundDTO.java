package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

public class FundDTO implements Serializable {
	private Long accountNumber;
	private Double amount;
	private String channel;
	private String sourceBankCode;
	private String sourceAccountNumber;
	private String destBankCode;
	private String pin;
	private String transRef;

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSourceBankCode() {
		return sourceBankCode;
	}
	public void setSourceBankCode(String sourceBankCode) {
		this.sourceBankCode = sourceBankCode;
	}
	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}
	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}
	public String getDestBankCode() {
		return destBankCode;
	}
	public void setDestBankCode(String destBankCode) {
		this.destBankCode = destBankCode;
	}

	public String getTransRef() {
		return transRef;
	}
	public void setTransRef(String transRef) {
		this.transRef = transRef;
	}

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }
}
