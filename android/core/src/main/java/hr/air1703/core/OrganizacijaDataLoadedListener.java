package hr.air1703.core;

import java.util.List;

import hr.air1703.database.model.Organizacija;

/**
 * Created by pvlahovic on 12.11.2017..
 */

public interface OrganizacijaDataLoadedListener {

    void onDataLoaded(List<Organizacija> organizacije);
    void onFailure(int messageCode);

}
