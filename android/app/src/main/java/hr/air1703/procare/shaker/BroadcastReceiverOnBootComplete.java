package hr.air1703.procare.shaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BroadcastReceiverOnBootComplete extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            Intent serviceIntent = new Intent(context, AndroidServiceStartOnBoot.class);

            // check for user settings
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Boolean shakeKey = sharedPreferences.getBoolean("pref_shakeKey", true);

            if (shakeKey) {
                context.startService(serviceIntent);
            }
        }
    }
}
