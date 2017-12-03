package hr.air1703.procare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karan.churi.PermissionManager.PermissionManager;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.air1703.core.poziv.PozivService;
import hr.air1703.core.poziv.PozivServiceHandler;
import hr.air1703.core.poziv.RazloziDataLoadedListener;
import hr.air1703.core.poziv.RazloziPozivaDataLoader;
import hr.air1703.database.model.Razlog;
import hr.air1703.database.settings.LocalApplicationLog;
import hr.air1703.procare.loaders.RazlogLocalDBDataLoader;
import hr.air1703.procare.loaders.RazlogWebDataLoader;
import hr.air1703.procare.poziv.PozivButtonService;
import hr.air1703.procare.utils.ApplicationUtils;
import hr.air1703.procare.utils.GPSPresenter;
import hr.air1703.procare.utils.GPSPresenter.GPSView;
import hr.air1703.procare.utils.GPSService;


public class HelpCallActivity extends AppCompatActivity implements GPSView,
        RazloziDataLoadedListener,
        PozivServiceHandler {

    public static Location location;
    private ProgressDialog progressDialog;
    @BindView(R.id.spinner_razlozi)
    Spinner razloziSpiner;
    @BindView(R.id.button_poziv_pomoci)
    Button buttonPozivPomoci;
    @BindView(R.id.gpsText)
    TextView locationText;

    // GPS klasa u kojoj je interface za lokaciju
    private GPSPresenter gpsPresenter;
    private IntentFilter intentFilter;

    // Manager for permissions
    PermissionManager permissionManager;

    private PozivService pozivService;
    private boolean calledFromShake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_call);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        // Check permissions
        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(this);

        loadRazloziData();
        pozivService = new PozivButtonService(this, buttonPozivPomoci, razloziSpiner);
        ((PozivButtonService)pozivService).setupButtonFunction();


        gpsPresenter = new GPSPresenter(this);
        displayProgress();
        startService(new Intent(this, GPSService.class));

        // get intent and check if it was sent from onShake
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            calledFromShake = extras.getBoolean("call");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register GPS receiver
        registerReceiver(broadcastReceiver, getIntentFilter());
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister GPS receiver
        unregisterReceiver(broadcastReceiver);
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
                R.layout.custom_spinner_text, razlozi);

        adapter.setDropDownViewResource(R.layout.custom_spinner_text);

        razloziSpiner.setAdapter(adapter);
        razloziSpiner.setSelection(0);
    }

    @Override
    public void onRazloziPozivaFailure(int messageCode) {
        showToast(String.valueOf(getText(R.string.error_infinitiv)) + ": "
                + String.valueOf(getText(messageCode)));
    }

    @Override
    public void onTimeDifferenceProblem(long timeDiff) {
        AlertDialog.Builder helpAlert = new AlertDialog.Builder(this);
        helpAlert.setTitle(R.string.alert_help_title);
        helpAlert.setMessage(getString(R.string.alert_help_message_prefix) + " " +
                String.valueOf(timeDiff) + " " + getString(R.string.alert_help_message_postfix));
        helpAlert.setPositiveButton(R.string.alert_help_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        helpAlert.create();
        helpAlert.show();
    }

    @Override
    public void onFiveSecondRule() {
        Toast.makeText(getApplicationContext(), R.string.help_message_button_press, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPozivSucceeded() {
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

    @Override
    public void setLocation(Location newLocation) {
        location = newLocation;

        if (calledFromShake) {
            sendCallForHelp();
        }
    }

    private void sendCallForHelp(){
        ((PozivButtonService) pozivService).callHelpFunction();
        Log.i("CALL", "Function sendCallForHelp triggered");
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

    private void displayProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage(getString(R.string.display_progress));
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private IntentFilter getIntentFilter() {
        if (intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction(GPSService.ACTION_LOCATION_CHANGED);
            intentFilter.addAction(GPSService.ACTION_LOCATION_FAILED);
            intentFilter.addAction(GPSService.ACTION_PROCESS_CHANGED);
        }
        return intentFilter;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(GPSService.ACTION_LOCATION_CHANGED)) {
                gpsPresenter.onLocationChanged((Location) intent.getParcelableExtra(GPSService.EXTRA_LOCATION));
            } else if (action.equals(GPSService.ACTION_LOCATION_FAILED)) {
                //noinspection WrongConstant
                gpsPresenter.onLocationFailed(intent.getIntExtra(GPSService.EXTRA_FAIL_TYPE, FailType.UNKNOWN));
            } else if (action.equals(GPSService.ACTION_PROCESS_CHANGED)) {
                //noinspection WrongConstant
                gpsPresenter.onProcessTypeChanged(intent.getIntExtra(GPSService.EXTRA_PROCESS_TYPE,
                        ProcessType.GETTING_LOCATION_FROM_CUSTOM_PROVIDER));
            }
        }
    };

    // Handle permission results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionManager.checkResult(requestCode, permissions, grantResults);
    }
}
