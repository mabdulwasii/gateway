package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.client.ExternalRestInlineClient;
import ng.com.systemspecs.apigateway.client.InlineStatusResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StatusCheckController {
    private final Logger log = LoggerFactory.getLogger(WalletAccountResource.class);

    @Autowired
    private ExternalRestInlineClient client;


    @GetMapping(path = "/status/{transactionId}")
    public String getStatus(@PathVariable("transactionId") String transactionId){
        String  secretKey =
            "23093b2bda801eece94fc6e8363c05fad90a4ba3e12045005141b0bab41704b3a148904529239afd1c9a3880d51b4018d13fd626b2cef77a6f858fe854834e54";

        String  inlinePmtStatusPublicKey  =
            "QzAwMDAyNzEyNTl8MTEwNjE4NjF8OWZjOWYwNmMyZDk3MDRhYWM3YThiOThlNTNjZTE3ZjYxOTY5NDdmZWE1YzU3NDc0ZjE2ZDZjNTg1YWYxNWY3NWM4ZjMzNzZhNjNhZWZlOWQwNmJhNTFkMjIxYTRiMjYzZDkzNGQ3NTUxNDIxYWNlOGY4ZWEyODY3ZjlhNGUwYTY=";

        String hash = new DigestUtils("SHA-512").digestAsHex(transactionId + secretKey);

        try{
           /* Map<String,String> headers =  new HashMap<>();
            headers.put("publicKey", inlinePmtStatusPublicKey);
            headers.put("Content-Type", "application/json");
            headers.put("TXN_HASH", hash);*/

            log.debug("hash:  "+hash);
            log.debug("publicKey:  "+inlinePmtStatusPublicKey);
            log.debug("secretKey:  "+secretKey);
//            log.debug("Headers:  "+ headers);

            String status = client.getStatus3(inlinePmtStatusPublicKey, hash, transactionId);
            log.debug("Response data : " + status);
            return status;

        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return  "";

    }

    @GetMapping(path = "/status2/{transactionId}")
    public InlineStatusResponse getStatus2(@PathVariable("transactionId") String transactionId){
        String  secretKey =
            "23093b2bda801eece94fc6e8363c05fad90a4ba3e12045005141b0bab41704b3a148904529239afd1c9a3880d51b4018d13fd626b2cef77a6f858fe854834e54";

        String  inlinePmtStatusPublicKey  =
            "QzAwMDAyNzEyNTl8MTEwNjE4NjF8OWZjOWYwNmMyZDk3MDRhYWM3YThiOThlNTNjZTE3ZjYxOTY5NDdmZWE1YzU3NDc0ZjE2ZDZjNTg1YWYxNWY3NWM4ZjMzNzZhNjNhZWZlOWQwNmJhNTFkMjIxYTRiMjYzZDkzNGQ3NTUxNDIxYWNlOGY4ZWEyODY3ZjlhNGUwYTY=";

        String hash = new DigestUtils("SHA-512").digestAsHex(transactionId + secretKey);

        String url = "https://remitademo.net/payment/v1/payment/query/{transId}";

        try{
            RestTemplate restTemplate = new RestTemplate();

            Map<String, String> params = new HashMap<String, String>();
            params.put("transId", transactionId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("publicKey", inlinePmtStatusPublicKey);
            headers.set("TXN_HASH", hash);

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            ResponseEntity<InlineStatusResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, InlineStatusResponse.class, params);

            log.debug(response.getHeaders().toString());
            log.debug(response.toString());
            return response.getBody();

        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return  null;
    }


}
