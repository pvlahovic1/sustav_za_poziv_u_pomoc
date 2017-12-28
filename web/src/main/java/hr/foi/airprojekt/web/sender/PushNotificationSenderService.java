package hr.foi.airprojekt.web.sender;

import java.util.Map;

public interface PushNotificationSenderService {

    void sendPushNotification(String token, String title, String message) throws Exception;

}
