package hr.air1703.procare.loaders;

import android.util.Log;

import java.util.Calendar;
import java.util.List;

import hr.air1703.core.OrganizacijaDataLoadedListener;
import hr.air1703.core.OrganizacijeDataLoader;
import hr.air1703.database.model.Organizacija;
import hr.air1703.database.model.TipOrganizacije;
import hr.air1703.database.settings.LocalApplicationLog;
import hr.air1703.webservice.remote.APIService;
import hr.air1703.webservice.remote.ApiUtils;
import hr.air1703.webservice.remote.wrapper.OrganizacijaTipWrapper;
import hr.air1703.webservice.remote.wrapper.OrganizacijaWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pvlahovic on 12.11.2017..
 */

public class OrganizacijaWebDataLoader extends OrganizacijeDataLoader {

    private APIService mAPIService;

    public OrganizacijaWebDataLoader() {
        this.mAPIService = ApiUtils.getAPIService();
    }

    @Override
    public void loadOrganizacije(final OrganizacijaDataLoadedListener organizacijaDataLoadedListener) {
        super.loadOrganizacije(organizacijaDataLoadedListener);

        mAPIService.getOrganizacije().enqueue(new Callback<List<OrganizacijaWrapper>>() {

            @Override
            public void onResponse(Call<List<OrganizacijaWrapper>> call, Response<List<OrganizacijaWrapper>> response) {
                if (response.isSuccessful()) {
                    List<OrganizacijaWrapper> oranizacije = response.body();
                    TipOrganizacije.deleteAll();
                    Organizacija.deleteAll();

                    for (OrganizacijaWrapper ow : oranizacije) {
                        Organizacija o = new Organizacija();
                        o.setNaziv(ow.getNaziv());
                        o.setOpis(ow.getOpis());
                        o.setBrojHitnih(ow.getBrojHitnih());
                        o.setBrojNehitnih(ow.getBrojNehitnih());
                        o.setX_koordinata(ow.getxkoordinata());
                        o.setY_koordinata(ow.getykoordinata());

                        o.save();

                        for (OrganizacijaTipWrapper otw : ow.getTipOrganizacijeList()) {
                            TipOrganizacije to = new TipOrganizacije();
                            to.setNaziv(otw.getNaziv());
                            to.setSlikaURL(otw.getSlikaURL());
                            to.setOrganizacijaId(o.getIdOrganizacija());

                            to.save();
                        }
                    }

                    if (LocalApplicationLog.getAll().isEmpty()) {
                        LocalApplicationLog localApplicationLog = new LocalApplicationLog();
                        localApplicationLog.setVrijemeDohvacanjaOrganizacija(Calendar.getInstance().getTime());
                        localApplicationLog.save();
                    } else {
                        LocalApplicationLog localApplicationLog = LocalApplicationLog.getAll().get(0);
                        localApplicationLog.setVrijemeDohvacanjaOrganizacija(Calendar.getInstance().getTime());
                        localApplicationLog.save();
                    }

                    Log.i("DataLoader", "Fetching data from WebServer");

                    organizacijaDataLoadedListener.onDataLoaded(Organizacija.getAll());
                }

            }

            @Override
            public void onFailure(Call<List<OrganizacijaWrapper>> call, Throwable t) {
                organizacijaDataLoadedListener.onFailure(hr.air1703.webservice.R.string.error_organizacije_fetch);
            }
        });

    }

}
