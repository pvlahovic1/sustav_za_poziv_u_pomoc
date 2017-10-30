package hr.air1703.procare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final EditText etIme = (EditText) findViewById(R.id.etIme);
        final EditText etPrezime = (EditText) findViewById(R.id.etPrezime);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etAdresa = (EditText) findViewById(R.id.etAdresa);
        final EditText etBrojMob = (EditText) findViewById(R.id.etBrojMob);
        final EditText etOIB = (EditText) findViewById(R.id.etOIB);
        final EditText pPassword = (EditText) findViewById(R.id.pPassword);

        final Button bRegister = (Button) findViewById(R.id.bSpremi);
    }
}
