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
import ng.com.systemspecs.apigateway.service.dto.BvnDTO;



@FeignClient(name = "external-service-bvn", url = "https://login.remita.net/remita/exapp/api/v1/ref/api/ext") 
public interface ExternalRESTClient2 {
	
 
	@RequestMapping(value = "/referencedata/bvn", method = RequestMethod.POST)
	@Headers("Content-Type: application/json")
	Object validateBvn(@RequestHeader Map<String,String> headers, @RequestBody  BvnDTO bvnDTO); 

}
