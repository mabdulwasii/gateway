package ng.com.systemspecs.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;
import feign.RequestLine;
import ng.com.systemspecs.apigateway.service.dto.BankTransfer;
import ng.com.systemspecs.apigateway.service.dto.OTPSendDTO;



@FeignClient(name = "external-otp-service", url = "http://192.168.2.50:6200/microservice/remita/gateway") 
public interface ExternalRESTOTPClient {
	

	@RequestMapping(value = "/send/api/notysvc/v2/sendsms.json", method = RequestMethod.POST)
	@Headers("Content-Type: application/json")
	void sendOTP(@RequestBody  OTPSendDTO otpSendDTO);
	

}
