package hr.air1703.procare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import hr.air1703.database.model.Korisnik;
import hr.air1703.procare.helper.MockData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText pPassword = (EditText) findViewById(R.id.pPassword);
        final Button bLogin = (Button) findViewById(R.id.bPrijava);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegistracija);

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
}
