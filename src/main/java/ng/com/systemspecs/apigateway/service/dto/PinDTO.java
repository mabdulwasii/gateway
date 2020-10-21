package ng.com.systemspecs.apigateway.service.dto;

import java.io.Serializable;

public class PinDTO implements Serializable{
	private String pin;
	private String phoneNumber;
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
