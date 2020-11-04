package ng.com.systemspecs.apigateway.service.fcm;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ng.com.systemspecs.apigateway.service.dto.PushNotificationRequest;

@Service
public class FCMService {
	private Logger logger = LoggerFactory.getLogger(FCMService.class);

	private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
		return FirebaseMessaging.getInstance().sendAsync(message).get();
	}

	private AndroidConfig getAndroidConfig(String topic) {
		return AndroidConfig.builder().setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
				.setPriority(AndroidConfig.Priority.HIGH)
				.setNotification(
						AndroidNotification.builder().setSound("default").setColor("#FFA500").setTag(topic).build())
				.build();
	}

	private ApnsConfig getApnsConfig(String topic) {
		return ApnsConfig.builder().setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
	}

	private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).setToken(request.getToken()).build();
	}

	private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).putAllData(data).setToken(request.getToken()).build();
	}

	private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
		Notification notification = Notification.builder().setTitle(request.getTitle()).setBody(request.getMessage())
				.build();

		AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
		ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
		return Message.builder().setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
				.setNotification(notification);
	}

	public void sendMessageWithoutData(PushNotificationRequest request)
			throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageWithoutData(request);
		String response = sendAndGetResponse(message);
		logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
	}

	private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic()).build();
	}

	public void sendMessageToToken(PushNotificationRequest request) throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageToToken(request);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		String response = sendAndGetResponse(message);
		logger.info(
				"Sent message to token. Device token: " + request.getToken() + ", " + response + " msg " + jsonOutput);
	}

	public void sendMessage(Map<String, String> data, PushNotificationRequest request)
			throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageWithData(data, request);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		String response = sendAndGetResponse(message);
		logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response + " msg " + jsonOutput);
	}
	
}
