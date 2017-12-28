package hr.air1703.procare;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by pvlahovic on 28.12.2017..
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
    }

    private void showNotification(String title, String message) {
        //// TODO: 28.12.2017.
    }

}
