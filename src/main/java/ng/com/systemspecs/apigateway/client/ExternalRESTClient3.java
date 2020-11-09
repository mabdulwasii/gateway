package ng.com.systemspecs.apigateway.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;
import feign.RequestLine;
import ng.com.systemspecs.apigateway.service.dto.BankTransfer;
import ng.com.systemspecs.apigateway.service.dto.NinFingerPrintDTO;





  
@FeignClient(name = "external-service-2", url = "http://192.168.19.50:9182/api/ext") 
public interface ExternalRESTClient3 { 
	
	@RequestMapping(value = "/referencedataninandfingerprint", method = RequestMethod.POST) 
	Object getFingerPrintData(@RequestHeader Map<String,String> headers, @RequestBody  NinFingerPrintDTO  ninFingerPrintDTO);
	// Object getFingerPrintData(@RequestHeader Map<String,String> headers, @RequestBody  NinFingerPrintDTO  ninFingerPrintDTO);

	
	
}
