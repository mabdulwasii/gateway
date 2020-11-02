package ng.com.systemspecs.apigateway.service.dto;

public class RegisterCompleteResponseDTO<T> {
	private String message;
	private T wallet;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getWallet() {
		return wallet;
	}
	public void setWallet(T wallet2) {
		this.wallet = wallet2;
	}
	

}
