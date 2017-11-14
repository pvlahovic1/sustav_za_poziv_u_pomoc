package hr.air1703.procare;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.air1703.core.APIResponseListener;
import hr.air1703.database.model.Korisnik;
import hr.air1703.procare.login.UserApi;
import hr.air1703.procare.utils.GPS_Service;
import hr.air1703.procare.utils.Hashing;

public class MainActivity extends AppCompatActivity  implements APIResponseListener {

    private EditText editTextMail;
    private EditText editTextPassword;

    //GPS
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        editTextMail = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.pPassword);

        //GPS start service
        Intent i =new Intent(getApplicationContext(),GPS_Service.class);
        startService(i);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Korisnik.getAll().isEmpty()) {
            showUserAreaActivity();
        }
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Bundle bundle = intent.getExtras();
                    String s = new String(bundle.getString("coordinates"));
                    Log.i("GPS", "Location:" + s);

                    String parts[] = s.split(" ");

                    //TODO: check if exists
                    Double longitude = Double.parseDouble(parts[0]);
                    Double latitude = Double.parseDouble(parts[1]);
                    //Double accuracy = Double.parseDouble(parts[2]);
                    Log.i("GPS", "Longitude: " + longitude);
                    Log.i("GPS", "Latitude: " + latitude);
                    //Log.i("GPS", "Accuracy: " + accuracy);
                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    @OnClick(R.id.tvRegistracija)
    public void textViewRegistracijaClicked(View view) {
        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
        MainActivity.this.startActivity(registerIntent);
    }

    @OnClick(R.id.bPrijava)
    public void buttonPrijavaClicked(View view) {
        Log.i("button", "Login button clicked.");
        String mail = editTextMail.getText().toString().trim();
        String lozinka = editTextPassword.getText().toString().trim();
        lozinka = Hashing.SHA1(lozinka);
        if (!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(lozinka)) {
            UserApi userApi = new UserApi(this);
            userApi.login(mail, lozinka);
        }
    }

    @Override
    public void onLoginSucceeded(Korisnik korisnik) {
        korisnik.save();
        showUserAreaActivity();
    }

    @Override
    public void onError(int messageCode) {
        Toast.makeText(getApplicationContext(),
                String.valueOf(getText(R.string.error_infinitiv)) + ": "
                        + String.valueOf(getText(messageCode)),
                Toast.LENGTH_LONG).show();
    }

    private void showUserAreaActivity() {
        Intent intent = new Intent(this, UserAreaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    // GPS permission check
    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                //enable_buttons();
            }else {
                runtime_permissions();
            }
        }
    }

}
