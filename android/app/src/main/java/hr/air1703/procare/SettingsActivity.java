package hr.air1703.procare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.regex.Matcher;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.air1703.core.APIResponseListener;
import hr.air1703.database.model.Korisnik;
import hr.air1703.procare.login.UserApi;
import hr.air1703.procare.utils.Hashing;

import static hr.air1703.procare.utils.ApplicationUtils.VALID_EMAIL_ADDRESS_REGEX;

public class SettingsActivity extends AppCompatActivity implements APIResponseListener {

    private Korisnik trenutniKorisnik;

    private TextView textViewOib;
    private EditText editTextIme;
    private EditText editTextPrezime;
    private EditText editTextEmail;
    private EditText editTextAdresa;
    private EditText editTextBrojMobitela;
    private EditText editTextLozinka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        textViewOib = (TextView) findViewById(R.id.textViewOIB);
        editTextIme = (EditText) findViewById(R.id.etIme);
        editTextPrezime = (EditText) findViewById(R.id.etPrezime);
        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextAdresa = (EditText) findViewById(R.id.etAdresa);
        editTextBrojMobitela = (EditText) findViewById(R.id.etBrojMob);
        editTextLozinka = (EditText) findViewById(R.id.pPassword);

        fillUserData();

    }

    @OnClick(R.id.buttonUpdate)
    public void buttonUpdateClicked() {
        boolean isOk = true;
        String ime = "";
        String prezime = "";
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
        if (TextUtils.isEmpty(editTextBrojMobitela.getText().toString().trim())) {
            editTextBrojMobitela.setError(getText(R.string.error_broj_mobitela_field));
            isOk = false;
        } else if(!isBrojMobitelaOk(editTextBrojMobitela.getText().toString().trim())) {
            editTextBrojMobitela.setError(getText(R.string.error_broj_mobitela_length));
            isOk = false;
        } else {
            brojMobitela = editTextBrojMobitela.getText().toString().trim();
        }

        lozinka = editTextLozinka.getText().toString();

            if (TextUtils.isEmpty(lozinka)) {
                lozinka = trenutniKorisnik.getLozinka();
            } else {
                if (lozinka.trim().length() < 5) {
                    editTextLozinka.setError(getText(R.string.error_lozinka_length_field));
                    isOk = false;
                } else {
                    lozinka = Hashing.SHA1(lozinka);
                }
            }

            if (isOk) {
                Korisnik korisnik = new Korisnik(trenutniKorisnik.getOib(), ime, prezime, adresa, email, lozinka, brojMobitela);
                UserApi userApi = new UserApi(this);
                userApi.update(korisnik);
            }
    }

    private boolean isBrojMobitelaOk(String brojMobitela) {
        return brojMobitela.length() > 8 && brojMobitela.length() < 13;
    }

    private boolean isMailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    private void fillUserData() {
        if (!Korisnik.getAll().isEmpty()) {
            trenutniKorisnik = Korisnik.getAll().get(0);

            textViewOib.setText(getText(R.string.oib) + ":" + trenutniKorisnik.getOib());
            editTextIme.setText(trenutniKorisnik.getIme());
            editTextPrezime.setText(trenutniKorisnik.getPrezime());
            editTextEmail.setText(trenutniKorisnik.getMail());
            editTextAdresa.setText(trenutniKorisnik.getAdresa());
            editTextBrojMobitela.setText(trenutniKorisnik.getBrojMob());
        }
    }


    @Override
    public void onLoginSucceeded(Korisnik korisnik) {
        trenutniKorisnik.setIme(korisnik.getIme());
        trenutniKorisnik.setPrezime(korisnik.getPrezime());
        trenutniKorisnik.setMail(korisnik.getMail());
        trenutniKorisnik.setLozinka(korisnik.getLozinka());
        trenutniKorisnik.setAdresa(korisnik.getAdresa());
        trenutniKorisnik.setBrojMob(korisnik.getBrojMob());

        trenutniKorisnik.update();
        Toast.makeText(getApplicationContext(),
                getText(R.string.update_succeeded), Toast.LENGTH_LONG).show();

        onBackPressed();
    }

    @Override
    public void onError(int messageCode) {
        Toast.makeText(getApplicationContext(),
                String.valueOf(getText(R.string.error_infinitiv)) + ": "
                        + String.valueOf(getText(messageCode)),
                Toast.LENGTH_LONG).show();
    }
}
