package hr.air1703.procare.poziv;

import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import hr.air1703.core.poziv.PozivService;
import hr.air1703.core.poziv.PozivServiceHandler;
import hr.air1703.database.model.Korisnik;
import hr.air1703.database.model.Razlog;
import hr.air1703.database.settings.LocalApplicationLog;
import hr.air1703.procare.HelpCallActivity;
import hr.air1703.procare.utils.ApplicationUtils;
import hr.air1703.webservice.remote.wrapper.PozivUPomocWrapper;

/**
 * Created by pvlahovic on 20.11.2017..
 */

public class PozivButtonService extends PozivService {

    private Button button;
    private Spinner spinner;
    private Date d1;

    public PozivButtonService(PozivServiceHandler pozivServiceHandler, Button button, Spinner spinner) {
        super(pozivServiceHandler);
        this.button = button;
        this.spinner = spinner;
    }

    public void setupButtonFunction() {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        d1 = Calendar.getInstance().getTime();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (ApplicationUtils.getDateDiff(d1, Calendar.getInstance().getTime(), TimeUnit.SECONDS) >= 5) {
                            callHelp();
                        } else {
                            pozivServiceHandler.onFiveSecondRule();
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void callHelp() {
        boolean canSendRequest = false;
        long timeDiff = 0;
        long minTimeDiff = 15;

        if (!LocalApplicationLog.getAll().isEmpty()) {
            LocalApplicationLog localApplicationLog = LocalApplicationLog.getAll().get(0);

            if (localApplicationLog.getVrijemeSlanjaPozivaUPomoc() != null) {
                timeDiff = ApplicationUtils.getDateDiff(localApplicationLog.getVrijemeSlanjaPozivaUPomoc(),
                        Calendar.getInstance().getTime(), TimeUnit.MINUTES);
                canSendRequest = timeDiff >= minTimeDiff;
            } else {
                canSendRequest = true;
            }
        } else {
            canSendRequest = true;
        }

        if (canSendRequest) {
            Location location = HelpCallActivity.location;
            if (location != null) {
                Razlog razlog = (Razlog) spinner.getSelectedItem();
                Korisnik korisnik = Korisnik.getAll().get(0);

                PozivUPomocWrapper poziv = new PozivUPomocWrapper(korisnik.getOib(),
                        razlog.getNaziv(), location.getLatitude(), location.getLongitude());

                PozivApi pozivApi = new PozivApi(pozivServiceHandler);

                pozivApi.sendPozivUPomoc(poziv);
            }
        } else {
            pozivServiceHandler.onTimeDifferenceProblem(minTimeDiff - timeDiff);
        }
    }
}
