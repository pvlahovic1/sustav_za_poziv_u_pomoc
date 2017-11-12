package hr.air1703.core;

/**
 * Created by pvlahovic on 12.11.2017..
 */

public abstract class OrganizacijeDataLoader {

    protected OrganizacijaDataLoadedListener organizacijaDataLoadedListener;

    public void loadOrganizacije(OrganizacijaDataLoadedListener organizacijaDataLoadedListener) {
        this.organizacijaDataLoadedListener = organizacijaDataLoadedListener;
    }

}
