package hr.air1703.procare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import hr.air1703.database.model.Korisnik;
import hr.air1703.database.remote.APIService;
import hr.air1703.database.remote.ApiUtils;
import hr.air1703.procare.helper.MockData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText pPassword = (EditText) findViewById(R.id.pPassword);
        final Button bLogin = (Button) findViewById(R.id.bPrijava);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegistracija);

        // API services
        mAPIService = ApiUtils.getAPIService();

        // login listener
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("BUTTON", "Login button clicked.");
                String mail = etEmail.getText().toString().trim();
                String lozinka = pPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(lozinka)){

                    // create new body
                    final Korisnik LoginPost = new Korisnik();
                    LoginPost.setMail(mail);
                    LoginPost.setLozinka(lozinka);

                    // send body to API
                    sendLogin(LoginPost);
                }
            }
        });

        //listener
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        FlowManager.init(new FlowConfig.Builder(this).build());

        if (Korisnik.getAll().isEmpty()) {
            Log.i("App", "Dodajem novog korisnika");
            MockData.writeAll();
        } else {
            Korisnik korisnik = Korisnik.getAll().get(0);
            Log.i("App", "Korisnik postoji: " + korisnik.getIme() + " " + korisnik.getPrezime());
        }
    }

    public void sendLogin(Korisnik LoginPost){
        mAPIService.sendLogin(LoginPost).enqueue(new Callback<Korisnik>() {
            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                if (response.isSuccessful()){
                    Log.i("API", "Login submitted to API. Response: " + response.body().toString());
                    Log.i("API", "Received from API. Response: " + response.body().toLog());
                }
                else if(response.code() == 400){
                    Log.i("API", "ERROR: Message code 400.");
                }
                else{
                    Log.i("API", "ERROR: Connection to API failed.");
                }
            }
            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {
                    Log.e("API", "Unable to submit post to API.");
            }
        });
    }
}
