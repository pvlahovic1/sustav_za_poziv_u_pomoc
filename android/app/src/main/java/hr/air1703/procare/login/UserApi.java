package hr.air1703.procare.login;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import hr.air1703.core.APIResponseListener;
import hr.air1703.database.model.Korisnik;
import hr.air1703.procare.R;
import hr.air1703.webservice.remote.APIService;
import hr.air1703.webservice.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pvlahovic on 31.10.2017..
 */
public class UserApi {

    private APIService mAPIService;
    private APIResponseListener APIResponseListener;

    public UserApi(APIResponseListener APIResponseListener) {
        this.APIResponseListener = APIResponseListener;
        this.mAPIService = ApiUtils.getAPIService();
    }

    public void login(String mail, String lozinka ) {
        Korisnik loginData = new Korisnik();
        loginData.setMail(mail);
        loginData.setLozinka(lozinka);

        mAPIService.sendLogin(loginData).enqueue(new Callback<Korisnik>() {

            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                if (response.isSuccessful()){
                    Log.i("API", "Login response: " + response.body().toString());
                    APIResponseListener.onLoginSucceeded(response.body());
                } else {
                    APIResponseListener.onError(R.string.message_bad_user_data);
                }
            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {
                APIResponseListener.onError(R.string.message_to_get_data_from_api);
            }

        });
    }

    public void register(final Korisnik korisnik) {
        mAPIService.sendRegister(korisnik).enqueue(new Callback<Korisnik>() {

            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                if (response.isSuccessful()){
                    Log.i("API", "Register response: " + response.body().toString());
                    APIResponseListener.onLoginSucceeded(response.body());
                } else {
                    String errorMessage = "";
                    try {
                         errorMessage = new String (response.errorBody().bytes());
                    } catch (IOException e) {
                        Log.e("API", "Greška bez error message", e);
                    }

                    if (!TextUtils.isEmpty(errorMessage) && errorMessage.contains("OIB")) {
                        APIResponseListener.onError(R.string.message_oib_already_exists);
                    } else if (!TextUtils.isEmpty(errorMessage) && errorMessage.contains("mail")) {
                        APIResponseListener.onError(R.string.message_mail_already_exists);
                    } else {
                        APIResponseListener.onError(R.string.message_bad_user_data);
                    }
                }
            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {
                APIResponseListener.onError(R.string.message_to_get_data_from_api);
            }

        });
    }

}
