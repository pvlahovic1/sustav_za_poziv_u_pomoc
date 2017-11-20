package hr.air1703.procare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.air1703.core.OrganizacijaDataLoadedListener;
import hr.air1703.core.OrganizacijeDataLoader;
import hr.air1703.database.model.Organizacija;
import hr.air1703.database.model.TipOrganizacije;
import hr.air1703.database.settings.LocalApplicationLog;
import hr.air1703.procare.loaders.OrganizacijaLocalDBDataLoader;
import hr.air1703.procare.loaders.OrganizacijaWebDataLoader;
import hr.air1703.procare.utils.ApplicationUtils;

public class UserAreaActivity extends AppCompatActivity implements OrganizacijaDataLoadedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        dohvatiOrganizacije();
    }

    private void dohvatiOrganizacije() {

        OrganizacijeDataLoader odl;

        if (!LocalApplicationLog.getAll().isEmpty()) {
            LocalApplicationLog localLog = LocalApplicationLog.getAll().get(0);

            if (localLog.getVrijemeDohvacanjaOrganizacija() != null) {
                if (ApplicationUtils.getDateDiff(localLog.getVrijemeDohvacanjaOrganizacija(),
                        Calendar.getInstance().getTime(), TimeUnit.MINUTES) > 5) {
                    odl = new OrganizacijaWebDataLoader();
                } else {
                    odl = new OrganizacijaLocalDBDataLoader();
                }
            } else {
                odl = new OrganizacijaWebDataLoader();
            }
        } else {
            odl = new OrganizacijaWebDataLoader();
        }

        odl.loadOrganizacije(this);
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

}
