package hr.air1703.procare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    @OnClick(R.id.bPostavke)
    public void buttonPostavkeClikced(View view) {
        Intent settingsIntent = new Intent(UserAreaActivity.this, SettingsActivity.class);
        UserAreaActivity.this.startActivity(settingsIntent);
    }

    @OnClick(R.id.bPozivPomoc)
    public void buttonPozivPomocClikced(View view) {
        Intent pozivPomocIntent = new Intent(UserAreaActivity.this, HelpCallActivity.class);
        UserAreaActivity.this.startActivity(pozivPomocIntent);
    }

    @OnClick(R.id.bListaHitnaPomoc)
    public void buttonListaHitnaPomocClicked(View view) {
        Intent listaHitnihPomoci = new Intent(UserAreaActivity.this, HospitalListActivity.class);
        UserAreaActivity.this.startActivity(listaHitnihPomoci);
    }

/*
    @Override
    public void onDataLoaded(List<Organizacija> organizacije) {
        for (Organizacija o : organizacije) {
            Log.i("service", o.toString());
            for(TipOrganizacije t : o.getTipOrganizacijeList()) {
                Log.i("service", t.toString());
            }
        }
    }

    @Override
    public void onFailure(int messageCode) {
        Log.i("service", getString(messageCode));
    }
*/

}
