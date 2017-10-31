package hr.air1703.procare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import hr.air1703.core.LoginListener;
import hr.air1703.database.model.Korisnik;
import hr.air1703.procare.login.UserApiLogin;

public class MainActivity extends AppCompatActivity  implements LoginListener {

    private EditText editTextMail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        if (!Korisnik.getAll().isEmpty()) {
            showUserAreaActivity();
        }

        editTextMail = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.pPassword);
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
        if (!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(lozinka)) {
            UserApiLogin userApiLogin = new UserApiLogin(this);
            userApiLogin.login(mail, lozinka);
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

}
