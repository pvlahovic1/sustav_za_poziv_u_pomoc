package hr.air1703.procare.poziv;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import hr.air1703.core.poziv.PozivServiceHandler;
import hr.air1703.database.model.Razlog;
import hr.air1703.procare.thread.ProgressBarStatusRunnable;
import hr.air1703.procare.utils.ApplicationUtils;

/**
 * Created by pvlahovic on 20.11.2017..
 */

public class PozivButtonService extends PozivService {

    private Button button;
    private Spinner spinner;
    private ProgressBar progressBar;
    private Handler handler;
    private ProgressBarStatusRunnable progressBarStatusRunnable;
    private int dateDiffInSeconds;
    private Date d1;

    public PozivButtonService(PozivServiceHandler pozivServiceHandler, Button button, Spinner spinner,
                              ProgressBar progressBar) {
        super(pozivServiceHandler);
        this.button = button;
        this.spinner = spinner;
        this.progressBar = progressBar;

        this.handler = new Handler();
        this.dateDiffInSeconds = 5;
    }

    public void setupButtonFunction() {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        progressBar.setMax(dateDiffInSeconds * 1000);
                        progressBar.setProgress(0);
                        progressBarStatusRunnable = new ProgressBarStatusRunnable(progressBar, handler, dateDiffInSeconds);
                        Thread t = new Thread(progressBarStatusRunnable);
                        t.start();
                        d1 = Calendar.getInstance().getTime();
                        break;
                    case MotionEvent.ACTION_UP:
                        progressBarStatusRunnable.interuptProgrssBar();
                        progressBar.setProgress(0);
                        if (ApplicationUtils.getDateDiff(d1, Calendar.getInstance().getTime(), TimeUnit.SECONDS) >= dateDiffInSeconds) {
                            callHelp((Razlog)spinner.getSelectedItem());
                        } else {
                            pozivServiceHandler.onFiveSecondRule();
                        }
                        break;
                }
                return false;
            }
        });
    }

}
