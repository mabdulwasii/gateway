package ng.com.systemspecs.apigateway.service.dto;

public class RegisteredUserDTO {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String password;
    private String email;
	private String deviceNotificationToken;

	public String getDeviceNotificationToken() {
		return deviceNotificationToken;
	}
	public void setDeviceNotificationToken(String deviceNotificationToken) {
		this.deviceNotificationToken = deviceNotificationToken;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
