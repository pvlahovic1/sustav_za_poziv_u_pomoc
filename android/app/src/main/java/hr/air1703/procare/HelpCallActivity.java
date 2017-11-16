package hr.air1703.procare;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.air1703.core.poziv.PozivResponseListener;
import hr.air1703.core.poziv.RazloziDataLoadedListener;
import hr.air1703.core.poziv.RazloziPozivaDataLoader;
import hr.air1703.database.model.Korisnik;
import hr.air1703.database.model.Razlog;
import hr.air1703.database.settings.LocalApplicationLog;
import hr.air1703.procare.loaders.RazlogLocalDBDataLoader;
import hr.air1703.procare.loaders.RazlogWebDataLoader;
import hr.air1703.procare.poziv.PozivApi;
import hr.air1703.procare.utils.ApplicationUtils;
import hr.air1703.procare.utils.GPSPresenter;
import hr.air1703.webservice.remote.wrapper.PozivUPomocWrapper;


public class HelpCallActivity extends LocationBaseActivity implements GPSPresenter.GPSView,
        RazloziDataLoadedListener,
        PozivResponseListener {

    private ProgressDialog progressDialog;
    private TextView locationText;
    private Location location;

    @BindView(R.id.spinner_razlozi)
    Spinner razloziSpiner;


    // GPS klasa u kojoj je interface za lokaciju
    private GPSPresenter gpsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_call);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        loadRazloziData();

        // Prikaz lokacije na view
        locationText = (TextView) findViewById(R.id.gpsText);
        gpsPresenter = new GPSPresenter(this);
        getLocation();
        getLocationManager().get();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gpsPresenter.destroy();
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return Configurations.defaultConfiguration("Gimme the permission!", "Would you mind to turn GPS on?");
    }

    @Override
    public void onLocationChanged(Location location) {
        gpsPresenter.onLocationChanged(location);
    }

    @Override
    public void onLocationFailed(@FailType int failType) {
        gpsPresenter.onLocationFailed(failType);
    }

    @Override
    public void onProcessTypeChanged(@ProcessType int processType) {
        gpsPresenter.onProcessTypeChanged(processType);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getLocationManager().isWaitingForLocation()
                && !getLocationManager().isAnyDialogShowing()) {
            displayProgress();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        dismissProgress();
    }

    private void displayProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage("Getting location...");
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void setText(String text) {
        locationText.setText(text);
    }

    @Override
    public void updateProgress(String text) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(text);
        }
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @OnClick(R.id.button_poziv_pomoci)
    public void onButtonPozoviPomocClicked() {
        if (location != null) {
            Razlog razlog = (Razlog) razloziSpiner.getSelectedItem();
            Korisnik korisnik = Korisnik.getAll().get(0);

            PozivUPomocWrapper poziv = new PozivUPomocWrapper(korisnik.getOib(),
                    razlog.getNaziv(), location.getAltitude(), location.getLongitude());

            PozivApi pozivApi = new PozivApi(this);

            pozivApi.sendPozivUPomoc(poziv);
        }
    }

    private void loadRazloziData() {
        RazloziPozivaDataLoader razloziPozivaDataLoader;

        if (!LocalApplicationLog.getAll().isEmpty()) {
            LocalApplicationLog localLog = LocalApplicationLog.getAll().get(0);

            if (localLog.getVrijemeDohvacanjaRazlogaPoziva() != null) {
                if (ApplicationUtils.getDateDiff(localLog.getVrijemeDohvacanjaRazlogaPoziva(),
                        Calendar.getInstance().getTime(), TimeUnit.MINUTES) > 5) {
                    razloziPozivaDataLoader = new RazlogWebDataLoader();
                } else {
                    razloziPozivaDataLoader = new RazlogLocalDBDataLoader();
                }
            } else {
                razloziPozivaDataLoader = new RazlogWebDataLoader();
            }
        } else {
            razloziPozivaDataLoader = new RazlogWebDataLoader();
        }

        razloziPozivaDataLoader.loadRazlozi(this);
    }

    @Override
    public void onRazloziPozivaDataLoaded(final List<Razlog> razlozi) {
        ArrayAdapter<Razlog> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, razlozi);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        razloziSpiner.setAdapter(adapter);
        razloziSpiner.setSelection(0);
    }

    @Override
    public void onRazloziPozivaFailure(int messageCode) {
        showToast(String.valueOf(getText(R.string.error_infinitiv)) + ": "
                + String.valueOf(getText(messageCode)));
    }

    @Override
    public void onPosivSucceeded() {
        Toast.makeText(getApplicationContext(), getString(R.string.succeeded_poziv_u_pomoc), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPozivFailure(int messageCode) {
        showToast(String.valueOf(getText(R.string.error_infinitiv)) + ": "
                + String.valueOf(getText(messageCode)));
    }


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
