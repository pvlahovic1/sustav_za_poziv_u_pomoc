package hr.air1703.procare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import hr.air1703.database.model.Korisnik;
import hr.air1703.procare.helper.MockData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
