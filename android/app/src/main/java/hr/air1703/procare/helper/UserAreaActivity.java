package hr.air1703.procare.helper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import hr.air1703.procare.R;
import hr.air1703.procare.RegisterActivity;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etIme = (EditText) findViewById(R.id.etIme);
        final EditText etPrezime = (EditText) findViewById(R.id.etPrezime);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final TextView tvWelcome = (TextView) findViewById(R.id.tvWelcome);

        final Button bSetting = (Button) findViewById(R.id.bPostavke);
        //listener
        bSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(UserAreaActivity.this, RegisterActivity.class);
                UserAreaActivity.this.startActivity(settingsIntent);
            }
        });
    }
}
