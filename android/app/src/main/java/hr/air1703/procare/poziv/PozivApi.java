package hr.air1703.procare.poziv;

import java.util.Calendar;

import hr.air1703.core.poziv.PozivResponseListener;
import hr.air1703.database.settings.LocalApplicationLog;
import hr.air1703.procare.R;
import hr.air1703.webservice.remote.APIService;
import hr.air1703.webservice.remote.ApiUtils;
import hr.air1703.webservice.remote.wrapper.PozivUPomocWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pvlahovic on 16.11.2017..
 */

public class PozivApi {

    private APIService mAPIService;
    private PozivResponseListener pozivResponseListener;

    public PozivApi(PozivResponseListener pozivResponseListener) {
        this.mAPIService = ApiUtils.getAPIService();
        this.pozivResponseListener = pozivResponseListener;
    }

    public void sendPozivUPomoc(PozivUPomocWrapper poziv) {

        mAPIService.sendPozivUPomoc(poziv).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    LocalApplicationLog localApplicationLog;
                    if (LocalApplicationLog.getAll().isEmpty()) {
                        localApplicationLog = new LocalApplicationLog();
                    } else {
                        localApplicationLog = LocalApplicationLog.getAll().get(0);
                    }

                    localApplicationLog.setVrijemeSlanjaPozivaUPomoc(Calendar.getInstance().getTime());
                    localApplicationLog.save();

                    pozivResponseListener.onPozivSucceeded();
                } else {
                    pozivResponseListener.onPozivFailure(R.string.error_sending_poziv_u_pomoc);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                pozivResponseListener.onPozivFailure(R.string.error_sending_poziv_u_pomoc);
            }
        });

    }

}
