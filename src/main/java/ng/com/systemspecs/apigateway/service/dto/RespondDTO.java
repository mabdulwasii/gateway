package ng.com.systemspecs.apigateway.service.dto;

public class RespondDTO<T> {
	private String message;
	private String token;
	private T user;
	public T getUser() {
		return user;
	}
	public void setUser(T user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
