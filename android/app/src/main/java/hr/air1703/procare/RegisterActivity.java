package hr.air1703.procare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.regex.Matcher;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.air1703.core.APIResponseListener;
import hr.air1703.database.model.Korisnik;
import hr.air1703.procare.login.UserApi;

import static hr.air1703.procare.utils.ApplicationUtils.VALID_EMAIL_ADDRESS_REGEX;

public class RegisterActivity extends AppCompatActivity implements APIResponseListener {

    private EditText editTextIme;
    private EditText editTextPrezime;
    private EditText editTextEmail;
    private EditText editTextAdresa;
    private EditText editTextOib;
    private EditText editTextBrojMobitela;
    private EditText editTextLozinka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        editTextIme = (EditText) findViewById(R.id.etIme);
        editTextPrezime = (EditText) findViewById(R.id.etPrezime);
        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextAdresa = (EditText) findViewById(R.id.etAdresa);
        editTextBrojMobitela = (EditText) findViewById(R.id.etBrojMob);
        editTextOib = (EditText) findViewById(R.id.etOIB);
        editTextLozinka = (EditText) findViewById(R.id.pPassword);
    }

    @OnClick(R.id.buttonRegistracija)
    public void buttonRegistracijaClicked() {
        boolean isOk = true;
        String ime = "";
        String prezime = "";
        String oib = "";
        String email = "";
        String brojMobitela = "";
        String lozinka = "";
        String adresa = "";

        if (TextUtils.isEmpty(editTextIme.getText().toString().trim())) {
            editTextIme.setError(getText(R.string.error_prezime_field));
            isOk = false;
        } else {
            ime = editTextIme.getText().toString().trim();
        }
        if (TextUtils.isEmpty(editTextPrezime.getText().toString().trim())) {
            editTextPrezime.setError(getText(R.string.error_prezime_field));
            isOk = false;
        } else {
            prezime = editTextPrezime.getText().toString().trim();
        }
        if (TextUtils.isEmpty(editTextEmail.getText().toString().trim())) {
            editTextEmail.setError(getText(R.string.error_email_field));
            isOk = false;
        } else if (!isMailValid(editTextEmail.getText().toString().trim())) {
            editTextEmail.setError(getText(R.string.error_email_validation));
            isOk = false;
        } else {
            email = editTextEmail.getText().toString().trim();
        }
        if (TextUtils.isEmpty(editTextAdresa.getText().toString().trim())) {
            editTextAdresa.setError(getText(R.string.error_adresa_field));
            isOk = false;
        } else {
            adresa = editTextAdresa.getText().toString().trim();
        }
        if (TextUtils.isEmpty(editTextOib.getText().toString().trim())) {
            editTextOib.setError(getText(R.string.error_oib_field));
            isOk = false;
        } else if(!isOibOk(editTextOib.getText().toString().trim())) {
            editTextOib.setError(getText(R.string.error_oib_validation));
            isOk = false;
        } else {
            oib = editTextOib.getText().toString().trim();
        }
        if (TextUtils.isEmpty(editTextBrojMobitela.getText().toString().trim())) {
            editTextBrojMobitela.setError(getText(R.string.error_broj_mobitela_field));
            isOk = false;
        } else if(!isBrojMobitelaOk(editTextBrojMobitela.getText().toString().trim())) {
            editTextBrojMobitela.setError(getText(R.string.error_broj_mobitela_length));
            isOk = false;
        } else {
            brojMobitela = editTextBrojMobitela.getText().toString().trim();
        }
        if (TextUtils.isEmpty(editTextLozinka.getText().toString().trim())) {
            editTextLozinka.setError(getText(R.string.error_lozinka_field));
            isOk = false;
        } else if(editTextLozinka.getText().toString().trim().length() < 5) {
            editTextLozinka.setError(getText(R.string.error_lozinka_length_field));
            isOk = false;
        } else {
            lozinka = editTextLozinka.getText().toString().trim();
        }

        if (isOk) {
            Korisnik korisnik = new Korisnik(oib, ime, prezime, adresa, email, lozinka, brojMobitela);

            UserApi userApi = new UserApi(this);
            userApi.register(korisnik);
        }

    }

    private boolean isOibOk(String oib) {
        return oib.length() == 11;
    }

    private boolean isBrojMobitelaOk(String brojMobitela) {
        if (brojMobitela.length() > 8 && brojMobitela.length() < 13) {
            return true;
        }

        return false;
    }

    private boolean isMailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    @Override
    public void onLoginSucceeded(Korisnik korisnik) {
        korisnik.save();
        Intent intent = new Intent(this, UserAreaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(int messageCode) {
        Toast.makeText(getApplicationContext(),
                String.valueOf(getText(R.string.error_infinitiv)) + ": "
                        + String.valueOf(getText(messageCode)),
                Toast.LENGTH_LONG).show();
    }

}
