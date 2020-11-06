package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

public class OTPSendDTO  implements Serializable {
	
	private String  mobileNumber;
	private String  smsMessage; 
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getSmsMessage() {
		return smsMessage;
	}
	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}
	 
	
	

}
