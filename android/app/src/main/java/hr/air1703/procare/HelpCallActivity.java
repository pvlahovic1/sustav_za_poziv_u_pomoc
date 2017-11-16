package hr.air1703.procare;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.yayandroid.locationmanager.LocationManager;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import hr.air1703.procare.utils.GPSPresenter;


public class HelpCallActivity extends LocationBaseActivity implements GPSPresenter.GPSView {

    private ProgressDialog progressDialog;
    private TextView locationText;

    // GPS klasa u kojoj je interface za lokaciju
    private GPSPresenter gpsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_call);

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
    public String getText() {
        return locationText.getText().toString();
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
}
