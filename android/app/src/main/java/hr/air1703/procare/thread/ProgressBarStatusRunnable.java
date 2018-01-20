package hr.air1703.procare.thread;

import android.os.Handler;
import android.widget.ProgressBar;

/**
 * Created by pvlahovic on 20.1.2018..
 */

public class ProgressBarStatusRunnable implements Runnable {

    private ProgressBar progressBar;
    private boolean isRunning;
    private Handler handler;
    private int length;

    public ProgressBarStatusRunnable(ProgressBar progressBar, Handler handler, int length) {
        this.progressBar = progressBar;
        isRunning = true;
        this.handler = handler;
        this.length = length;
    }

    @Override
    public void run() {
        final int progrssTime = length * 1000;

        for (int i = 0; i < progrssTime; i++) {
            if (isRunning) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (progressBar.getProgress() < progrssTime) {
                            progressBar.setProgress(progressBar.getProgress() + 10);
                        }
                    }
                });
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
    }

    public void interuptProgrssBar() {
        this.isRunning = false;
    }

}
