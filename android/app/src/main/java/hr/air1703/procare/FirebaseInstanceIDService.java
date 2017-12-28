package hr.air1703.procare;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.List;

import hr.air1703.database.model.Korisnik;
import hr.air1703.procare.login.TokenApi;

/**
 * Created by pvlahovic on 28.12.2017..
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i("token", token);
        List<Korisnik> korisnici = Korisnik.getAll();
        
        if (!korisnici.isEmpty()) {
            TokenApi tokenApi = new TokenApi(getApplicationContext());
            tokenApi.updateToken(token);
        } else {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("messageToken", token);
            editor.apply();
        }

    }
}
