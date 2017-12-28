package hr.foi.airprojekt.web.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
public class PushNotificationSenderServiceImpl implements PushNotificationSenderService {

    @Override
    public void sendPushNotification(String token, String title, String message) throws Exception {
        sendPostRequest(token, title, message);
    }

    private void sendPostRequest(String token, String title, String message) throws Exception {
        HttpURLConnection httpURLConnection = createHttpConnection("https://fcm.googleapis.com/fcm/send");

        String postJsonData = "{\"to\" : \"" + token + "\", \"data\":{\"message\":\"" + message + "\", \"title\":\"" + title + "\"}}";

        httpURLConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();

        log.info("Connection code: {}", httpURLConnection.getResponseCode());

        BufferedReader in = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream()));
        String output;
        StringBuilder response = new StringBuilder();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        log.info("Response body: {}", response.toString());
    }

    private HttpURLConnection createHttpConnection(String adress) throws Exception {
        URL url = new URL(adress);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "key=AAAAQBOLS28:APA91bFwVmjf-Ow5XpFWKHfqbTxu8lZpthD5YkjX2kzpS-IVP9QKcCmK4Hgk3XPPm9R87akSWErG3zWcXMUeMUYtig_4_rh9qWIsUCh2EqG5H52QioxpU-kchso47yyBRbigx17AanQS");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
        log.info("Opening connection to: " + adress);
        return httpURLConnection;
    }

}
