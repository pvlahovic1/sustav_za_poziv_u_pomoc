package hr.air1703.procare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import hr.air1703.procare.shaker.AccelerometerManager;
import hr.air1703.procare.shaker.AndroidServiceStartOnBoot;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_preferences);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        // Check for shakeKey
        if (key.equals("pref_shakeKey")){
            Boolean shakeKey = sharedPreferences.getBoolean(key, true);

            Intent serviceIntent = new Intent(this, AndroidServiceStartOnBoot.class);
            // start shake service
            if (shakeKey){
                if (!AccelerometerManager.isListening()){
                    this.startService(serviceIntent);
                }
            }
            // stop shake service
            else{
                if (AccelerometerManager.isListening()){
                    this.stopService(serviceIntent);
                    AccelerometerManager.stopListening();
                }
            }
            savePreferences(key, sharedPreferences.getBoolean(key, true));
        }
    }

    public void savePreferences(String key, Boolean value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
