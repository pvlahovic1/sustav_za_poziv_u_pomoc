package hr.air1703.procare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import hr.air1703.core.OrganizacijaDataLoadedListener;
import hr.air1703.core.OrganizacijeDataLoader;
import hr.air1703.database.model.Organizacija;
import hr.air1703.database.settings.LocalApplicationLog;
import hr.air1703.procare.adapters.ExpandableHospitalsItem;
import hr.air1703.procare.adapters.HospitalsRecycleAdapter;
import hr.air1703.procare.loaders.OrganizacijaLocalDBDataLoader;
import hr.air1703.procare.loaders.OrganizacijaWebDataLoader;
import hr.air1703.procare.utils.ApplicationUtils;

public class HospitalListActivity extends AppCompatActivity implements OrganizacijaDataLoadedListener{

    private HospitalsRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

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

    @Override
    public void onDataLoaded(List<Organizacija> organizacije) {

        List<ExpandableHospitalsItem> hospitalsItemList = new ArrayList<ExpandableHospitalsItem>();

        if(organizacije != null) {
            for (Organizacija o : organizacije) {
                Log.i("service", o.toString());
                hospitalsItemList.add(new ExpandableHospitalsItem(o));
                RecyclerView mRecycler = (RecyclerView) findViewById(R.id.main_recycler);
                if(mRecycler != null) {
                    adapter = new HospitalsRecycleAdapter(this, hospitalsItemList);
                    mRecycler.setAdapter(adapter);
                    mRecycler.setLayoutManager(new LinearLayoutManager(this));

                    adapter.expandParent(0);
                }
            }
        }
    }

    @Override
    public void onFailure(int messageCode) {
        Log.i("service", getString(messageCode));
    }
}