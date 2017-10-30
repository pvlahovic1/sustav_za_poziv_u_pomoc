package hr.air1703.procare.helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import hr.air1703.procare.R;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etIme = (EditText) findViewById(R.id.etIme);
        final EditText etPrezime = (EditText) findViewById(R.id.etPrezime);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final TextView tvWelcome = (TextView) findViewById(R.id.tvWelcome);
    }
}
