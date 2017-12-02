package hr.air1703.procare.shaker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

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
        Toast.makeText(this, "Motion detected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {}
}
