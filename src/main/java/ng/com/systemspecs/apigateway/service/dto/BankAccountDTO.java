package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

public class BankAccountDTO implements Serializable {

	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getPushNotificationId() {
		return pushNotificationId;
	}
	public void setPushNotificationId(String pushNotificationId) {
		this.pushNotificationId = pushNotificationId;
	}
	public String getLinkedEmail() {
		return linkedEmail;
	}
	public void setLinkedEmail(String linkedEmail) {
		this.linkedEmail = linkedEmail;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getRequestChannel() {
		return requestChannel;
	}
	public void setRequestChannel(String requestChannel) {
		this.requestChannel = requestChannel;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getProfiled() {
		return profiled;
	}
	public void setProfiled(String profiled) {
		this.profiled = profiled;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	
	private String  mobileNumber;
	private String  companyId;
	private String  pushNotificationId;
	private String  linkedEmail;
	private String  bankCode;
	private String  requestChannel;
	private String  accountNumber;
	private String  email;
	private String  accountType;
	private String  deviceId;
	private String  profiled;
	private String  compId;
	
	 
}
