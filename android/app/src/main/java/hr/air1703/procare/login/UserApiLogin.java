package hr.air1703.procare.login;

import android.util.Log;

import hr.air1703.core.LoginListener;
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
public class UserApiLogin {

    private APIService mAPIService;
    private LoginListener loginListener;

    public UserApiLogin(LoginListener loginListener) {
        this.loginListener = loginListener;
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
                    loginListener.onLoginSucceeded(response.body());
                } else {
                    loginListener.onError(R.string.message_bad_user_data);
                }
            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {
                loginListener.onError(R.string.message_to_get_data_from_api);
            }

        });
    }

}
