package ng.com.systemspecs.apigateway.service.dto;

public class PaymentResponseDTO {
	private String message;
	private String code;
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
