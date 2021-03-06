package hr.air1703.procare.poziv;

import android.location.Location;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import hr.air1703.core.poziv.PozivServiceHandler;
import hr.air1703.database.model.Korisnik;
import hr.air1703.database.model.Razlog;
import hr.air1703.procare.HelpCallActivity;
import hr.air1703.procare.R;
import hr.air1703.procare.utils.ApplicationUtils;
import hr.air1703.core.sharedpreferences.SharedPreferencesWorker;
import hr.air1703.webservice.remote.wrapper.PozivUPomocWrapper;

/**
 * Created by pvlahovic on 20.11.2017..
 */

public abstract class PozivService {

    protected PozivServiceHandler pozivServiceHandler;

    public PozivService(PozivServiceHandler pozivServiceHandler) {
        this.pozivServiceHandler = pozivServiceHandler;
    }

    protected void callHelp(Razlog razlog) {
        boolean canSendRequest = false;
        long timeDiff = 0;
        long minTimeDiff = 15;

        SharedPreferencesWorker sharedPreferencesWorker = SharedPreferencesWorker.getInstance();

        timeDiff = ApplicationUtils.getDateDiff(sharedPreferencesWorker.getVrijemeSlanjaPozivaUPomoc(),
                Calendar.getInstance().getTime(), TimeUnit.MINUTES);

        canSendRequest = timeDiff >= minTimeDiff;

        if (razlog != null) {
            if (canSendRequest) {
                Location location = HelpCallActivity.location;
                if (location != null) {
                    Korisnik korisnik = Korisnik.getAll().get(0);

                    PozivUPomocWrapper poziv = new PozivUPomocWrapper(korisnik.getOib(),
                            razlog.getNaziv(), location.getLatitude(), location.getLongitude());

                    PozivApi pozivApi = new PozivApi(pozivServiceHandler);

                    pozivApi.sendPozivUPomoc(poziv);
                } else {
                    pozivServiceHandler.onPozivFailure(R.string.error_lokacija_nije_dohvacena);
                }
            } else {
                pozivServiceHandler.onTimeDifferenceProblem(minTimeDiff - timeDiff);
            }
        } else {
            pozivServiceHandler.onPozivFailure(R.string.error_razlog_nije_naveden);
        }
    }
}
