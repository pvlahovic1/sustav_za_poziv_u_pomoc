package hr.air1703.procare.shaker;

public interface AccelerometerListener {
    public void onAccelerationChanged(float x, float y, float z);

    public void onShake(float force);
}
