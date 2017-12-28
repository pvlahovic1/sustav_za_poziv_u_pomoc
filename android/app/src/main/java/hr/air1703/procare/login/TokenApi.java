package hr.air1703.procare.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import hr.air1703.database.model.Korisnik;
import hr.air1703.webservice.remote.APIService;
import hr.air1703.webservice.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pvlahovic on 28.12.2017..
 */

public class TokenApi {
    private APIService mAPIService;
    private Context context;

    public TokenApi(Context context) {
        this.context = context;
        this.mAPIService = ApiUtils.getAPIService();
    }

    public void updateToken(final String token) {
        Korisnik korisnik  = Korisnik.getAll().get(0);
        korisnik.setMessageToken(token);

        mAPIService.sendUpdate(korisnik).enqueue(new Callback<Korisnik>() {

            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("messageToken");
                editor.apply();
            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("messageToken", token);
                editor.apply();
            }
        });
    }

}
