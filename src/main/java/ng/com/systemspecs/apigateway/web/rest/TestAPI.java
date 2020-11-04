package ng.com.systemspecs.apigateway.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ng.com.systemspecs.apigateway.service.dto.PushNotificationRequest;
import ng.com.systemspecs.apigateway.service.fcm.PushNotificationService;



@RestController
@RequestMapping("/api")
public class TestAPI {
	private final PushNotificationService pushNotificationService;
	
	public TestAPI(PushNotificationService pushNotificationService) {
		this.pushNotificationService = pushNotificationService;
	}
	
   @PostMapping("/send-notification-topic")
   public String SendNotificationTopic(@Valid @RequestBody PushNotificationRequest request) {
	   pushNotificationService.sendPushNotificationWithoutData(request); //(request);
	   return "success";
   }
   
   @PostMapping("/send-notification-token")
   public String SendNotificationToken(@Valid @RequestBody PushNotificationRequest request) {
	   pushNotificationService.sendPushNotificationToToken(request);//(request); //(request);
	   return "success";
   }
}
