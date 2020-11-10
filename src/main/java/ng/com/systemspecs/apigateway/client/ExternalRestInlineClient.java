package ng.com.systemspecs.apigateway.client;

import feign.HeaderMap;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import ng.com.systemspecs.apigateway.service.dto.BankTransfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "checkStatus", url = "https://remitademo.net/payment/v1/payment")
public interface ExternalRestInlineClient {

    @RequestMapping(value = "/query/{transId}", method = RequestMethod.GET)
    public String getStatus(@PathVariable("transId") String transId,
                            @HeaderMap Map<String, String> headerMap);

    @RequestMapping(value = "/query/{transId}", method = RequestMethod.GET)
    public String getStatus2(@RequestHeader("publicKey") String publicKey,
                             @RequestHeader("Content-Type") String contentType,
                             @RequestHeader("TXN_HASH") String hash,
                             @PathVariable("transId") String transId);

    @RequestMapping(value = "/query/{transId}", method = RequestMethod.GET)
    @Headers({"publicKey: {publicKey}", "Content-Type: application/json", "TXN_HASH: {hash}"})
        public String getStatus3(@RequestHeader("publicKey") String publicKey,
                                 @RequestHeader("TXN_HASH") String hash,
                                 @PathVariable("transId") String transId);

   /* @RequestMapping(value = "/query/{transId}", method = RequestMethod.GET)
    @Headers({"publicKey: {publicKey}", "Content-Type: application/json", "TXN_HASH: {hash}"})
    public String getStatus4(@Param("publicKey") String publicKey,
                             @Param("TXN_HASH") String hash,
                             @PathVariable("transId") String transId);*/



}
