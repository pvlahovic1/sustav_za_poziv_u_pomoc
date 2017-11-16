package hr.air1703.procare.utils;

import android.location.Location;
import android.text.TextUtils;
import android.util.Log;

import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

public class GPSPresenter {

    private GPSView gpsView;

    public GPSPresenter(GPSView view) {
        this.gpsView = view;
    }

    public void destroy() {
        gpsView = null;
    }

    public void onLocationChanged(Location location) {
        gpsView.dismissProgress();
        setText(location);
    }

    public void onLocationFailed(@FailType int failType) {
        gpsView.dismissProgress();

        switch (failType) {
            case FailType.TIMEOUT: {
                gpsView.setText("Couldn't get location, and timeout!");
                break;
            }
            case FailType.PERMISSION_DENIED: {
                gpsView.setText("Couldn't get location, because user didn't give permission!");
                break;
            }
            case FailType.NETWORK_NOT_AVAILABLE: {
                gpsView.setText("Couldn't get location, because network is not accessible!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_NOT_AVAILABLE: {
                gpsView.setText("Couldn't get location, because Google Play Services not available!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_CONNECTION_FAIL: {
                gpsView.setText("Couldn't get location, because Google Play Services connection failed!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_SETTINGS_DIALOG: {
                gpsView.setText("Couldn't display settingsApi dialog!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_SETTINGS_DENIED: {
                gpsView.setText("Couldn't get location, because user didn't activate providers via settingsApi!");
                break;
            }
            case FailType.VIEW_DETACHED: {
                gpsView.setText("Couldn't get location, because in the process view was detached!");
                break;
            }
            case FailType.VIEW_NOT_REQUIRED_TYPE: {
                gpsView.setText("Couldn't get location, "
                        + "because view wasn't sufficient enough to fulfill given configuration!");
                break;
            }
            case FailType.UNKNOWN: {
                gpsView.setText("Ops! Something went wrong!");
                break;
            }
        }
    }

    public void onProcessTypeChanged(@ProcessType int newProcess) {
        switch (newProcess) {
            case ProcessType.GETTING_LOCATION_FROM_GOOGLE_PLAY_SERVICES: {
                gpsView.updateProgress("Getting Location from Google Play Services...");
                break;
            }
            case ProcessType.GETTING_LOCATION_FROM_GPS_PROVIDER: {
                gpsView.updateProgress("Getting Location from GPS...");
                break;
            }
            case ProcessType.GETTING_LOCATION_FROM_NETWORK_PROVIDER: {
                gpsView.updateProgress("Getting Location from Network...");
                break;
            }
            case ProcessType.ASKING_PERMISSIONS:
            case ProcessType.GETTING_LOCATION_FROM_CUSTOM_PROVIDER:
                // Ignored
                break;
        }
    }

    private void setText(Location location) {
        String appendValue = location.getLatitude() + ", " + location.getLongitude() + ", " + location.getAccuracy() + "\n";
        String newValue;
        CharSequence current = gpsView.getText();

        if (!TextUtils.isEmpty(current)) {
            newValue = appendValue;
        } else {
            newValue = appendValue;
        }
        Log.i("GPS", appendValue);

        gpsView.setText(newValue);
    }

    public interface GPSView {

        String getText();

        void setText(String text);

        void updateProgress(String text);

        void dismissProgress();

    }

}
