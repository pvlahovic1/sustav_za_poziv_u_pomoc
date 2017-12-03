package hr.air1703.procare.shaker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import hr.air1703.procare.R;

public class AndroidServiceStartOnBoot extends Service implements AccelerometerListener {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (AccelerometerManager.isSupported(this)){
            AccelerometerManager.startListening(this);
        }
    }

    @Override
    public void onShake(float force) {
        Toast.makeText(this, getString(R.string.onShake_call), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent("android.intent.category.LAUNCHER");
        intent.setClassName("hr.air1703.procare", "hr.air1703.procare.HelpCallActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // send confirmation to activity that it was called from shake intent
        intent.putExtra("call", true );
        startActivity(intent);
    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {}
}
