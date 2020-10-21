package ng.com.systemspecs.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;
import feign.RequestLine;
import ng.com.systemspecs.apigateway.service.dto.BankTransfer;

@FeignClient(name = "external-service", url = "https://login.remita.net/remita/exapp/api/v1/wallet/services/"
		+ "core-banking/v1/transaction")
public interface ExternalRESTClient {

	@RequestMapping(value = "/confirmation/{transactionReference}", method = RequestMethod.GET)
	public String getTransactionConfirmation(@PathVariable("transactionReference") String transactionReference);

	@RequestMapping(value = "/singleInterbankTransfer", method = RequestMethod.POST)
	@Headers("Content-Type: application/json")
	void singleInterbankTransfer(BankTransfer bankTransfer);
}
