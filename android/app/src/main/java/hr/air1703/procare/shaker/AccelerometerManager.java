package hr.air1703.procare.shaker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class AccelerometerManager extends Service {

    /**
     * Accuracy configuration
     */
    private static float sensibility = 2.0f;
    private static int interval = 1000;
    private static int shakeCount = 5;
    private static int callToActionInterval = 60000;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static Context context = null;

    private static SensorManager sensorManager;
    private static AccelerometerListener listener;

    /**
     * indicates whether or not Accelerometer Sensor is supported
     */
    private static Boolean supported;
    /**
     * indicates whether or not Accelerometer Sensor is running
     */
    private static boolean running = false;

    /**
     * Returns true if the manager is listening to orientation changes
     */
    public static boolean isListening() {
        return running;
    }

    /**
     * Unregisters listeners
     */
    public static void stopListening() {
        running = false;
        try {
            if (sensorManager != null && sensorEventListener != null) {
                sensorManager.unregisterListener(sensorEventListener);
            }
        } catch (Exception e) {
            Log.i("Exception", "Exception: "+e);
        }
    }

    /**
     * Returns true if at least one Accelerometer sensor is available
     */
    public static boolean isSupported(Context cntxt) {
        context = cntxt;
        if (supported == null) {
            if (context != null) {

                sensorManager = (SensorManager) context.
                        getSystemService(Context.SENSOR_SERVICE);

                List<Sensor> sensors = sensorManager != null ? sensorManager.getSensorList(
                        Sensor.TYPE_ACCELEROMETER) : null;

                supported = (sensors != null ? sensors.size() : 0) > 0;
            } else {
                supported = Boolean.FALSE;
            }
        }
        return supported;
    }

    /**
     * Configure the listener for shaking
     *
     * @param sensibility minimum acceleration variation for considering shaking
     * @param interval maximal interval between shake events
     * @param shakeCount minimal number of shakes required for action
     */
    public static void configure(float sensibility, int interval, int shakeCount) {
        AccelerometerManager.sensibility = sensibility;
        AccelerometerManager.interval = interval;
        AccelerometerManager.shakeCount = shakeCount;
    }

    /**
     * Registers a listener and start listening
     *
     * @param accelerometerListener callback for accelerometer events
     */
    public static void startListening(AccelerometerListener accelerometerListener) {

        sensorManager = (SensorManager) context.
                getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensors = sensorManager != null ? sensorManager.getSensorList(
                Sensor.TYPE_ACCELEROMETER) : null;

        if ((sensors != null ? sensors.size() : 0) > 0) {

            Sensor sensor = sensors != null ? sensors.get(0) : null;

            running = sensorManager != null && sensorManager.registerListener(
                    sensorEventListener, sensor,
                    SensorManager.SENSOR_DELAY_GAME);

            listener = accelerometerListener;
        }
    }

    /**
     * Configures threshold and interval
     * And registers a listener and start listening
     *
     * @param accelerometerListener callback for accelerometer events
     * @param sensibility minimum acceleration variation for considering shaking
     * @param interval maximal interval between shake events
     * @param shakeCount minimal number of shakes required for action
     */
    public static void startListening(AccelerometerListener accelerometerListener, float sensibility, int interval, int shakeCount) {
        configure(sensibility, interval, shakeCount);
        startListening(accelerometerListener);
    }

    private static SensorEventListener sensorEventListener = new SensorEventListener() {

        private static final int SHAKE_SLOP_TIME_MS = 500;
        private long mShakeTimestamp;
        private int mShakeCount;
        private long lastCalledActionTimestamp;

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > sensibility) {

                Log.d("LISTENER", "force: " + gForce + " count: " + mShakeCount);

                final long now = System.currentTimeMillis();

                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }

                if (mShakeTimestamp + interval < now) {
                    mShakeCount = 0;
                }

                mShakeTimestamp = now;
                mShakeCount++;

                if (shakeCount == mShakeCount) {
                    if (now - lastCalledActionTimestamp > callToActionInterval) {
                        lastCalledActionTimestamp = now;
                        listener.onShake(gForce);
                    }
                }
            }
        }

    };
}