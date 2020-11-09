package ng.com.systemspecs.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;
import feign.RequestLine;
import ng.com.systemspecs.apigateway.service.dto.BankTransfer;

// 'https://remitademo.net/remita/ecomm/send/api/billing/receipt 
@FeignClient(name = "external-service", url = "https://remitademo.net/remita/ecomm/send/api") 
public interface ExternalRESTClient {

	@RequestMapping(value = "/confirmation/{transactionReference}", method = RequestMethod.GET)
	public String getTransactionConfirmation(@PathVariable("transactionReference") String transactionReference);

	@RequestMapping(value = "/singleInterbankTransfer", method = RequestMethod.POST)
	@Headers("Content-Type: application/json")
	void singleInterbankTransfer(BankTransfer bankTransfer);
	
	
	// {{baseUrl}}/{{publicKey}}/{{rrr}}/{{requestId}}/rest.reg
	@RequestMapping(value = "/billing/receipt/{publicKey}/{rrr}/{requestId}/rest.reg", method = RequestMethod.GET)
	byte[]   getRRRReceipt(@PathVariable("publicKey") String publicKey, @PathVariable("rrr") String rrr, @PathVariable("requestId") String requestId);
}
