package hr.air1703.webservice.remote.loaders;

import android.util.Log;

import hr.air1703.core.OrganizacijaDataLoadedListener;
import hr.air1703.core.OrganizacijeDataLoader;
import hr.air1703.database.model.Organizacija;

/**
 * Created by pvlahovic on 12.11.2017..
 */

public class OrganizacijaLocalDBDataLoader extends OrganizacijeDataLoader {

    @Override
    public void loadOrganizacije(OrganizacijaDataLoadedListener organizacijaDataLoadedListener) {
        super.loadOrganizacije(organizacijaDataLoadedListener);

        try {
            Log.i("DataLoader", "Fetching data from LocalDatabase");
            organizacijaDataLoadedListener.onDataLoaded(Organizacija.getAll());
        } catch (Exception e) {
            Log.e("DataLoader", e.getMessage());
        }
    }
}
