package ng.com.systemspecs.apigateway.service.dto;

public class PaymentResponseDTO {
	private String message;
	private String code;
	private Boolean error;
	public Boolean getError() {
		return error;
	}
	public void setError(Boolean error) {
		this.error = error;
	}
	public PaymentResponseDTO() {
		this.error = false;
	}
	private PaymentTransactionDTO paymentTransactionDTO;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PaymentTransactionDTO getPaymentTransactionDTO() {
		return paymentTransactionDTO;
	}
	public void setPaymentTransactionDTO(PaymentTransactionDTO paymentTransactionDTO) {
		this.paymentTransactionDTO = paymentTransactionDTO;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
