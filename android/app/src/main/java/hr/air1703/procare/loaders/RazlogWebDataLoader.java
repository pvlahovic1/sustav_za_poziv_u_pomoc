package hr.air1703.procare.loaders;

import java.util.Calendar;
import java.util.List;

import hr.air1703.core.poziv.RazloziDataLoadedListener;
import hr.air1703.core.poziv.RazloziPozivaDataLoader;
import hr.air1703.database.model.Razlog;
import hr.air1703.database.settings.LocalApplicationLog;
import hr.air1703.procare.R;
import hr.air1703.webservice.remote.APIService;
import hr.air1703.webservice.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pvlahovic on 16.11.2017..
 */

public class RazlogWebDataLoader extends RazloziPozivaDataLoader {

    private APIService mAPIService;

    public RazlogWebDataLoader() {
        this.mAPIService = ApiUtils.getAPIService();
    }

    @Override
    public void loadRazlozi(final RazloziDataLoadedListener razloziDataLoadedListener) {
        super.loadRazlozi(razloziDataLoadedListener);

        mAPIService.getRazloziPozivaUPomoc().enqueue(new Callback<List<Razlog>>() {
            @Override
            public void onResponse(Call<List<Razlog>> call, Response<List<Razlog>> response) {
                if (response.isSuccessful()) {
                    Razlog.deleteAll();

                    List<Razlog> razlozi = response.body();

                    for (Razlog razlog : razlozi) {
                        razlog.save();
                    }

                    LocalApplicationLog localApplicationLog;
                    if (LocalApplicationLog.getAll().isEmpty()) {
                        localApplicationLog = new LocalApplicationLog();
                    } else {
                        localApplicationLog = LocalApplicationLog.getAll().get(0);
                    }

                    localApplicationLog.setVrijemeDohvacanjaRazlogaPoziva(Calendar.getInstance().getTime());
                    localApplicationLog.save();

                    razloziDataLoadedListener.onDataLoaded(Razlog.getAll());
                } else {
                    razloziDataLoadedListener.onFailure(R.string.error_razlozi_service_fail);
                }
            }

            @Override
            public void onFailure(Call<List<Razlog>> call, Throwable t) {
                razloziDataLoadedListener.onFailure(R.string.error_razlozi_service_fail);
            }
        });

    }
}
