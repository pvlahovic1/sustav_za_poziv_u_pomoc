package hr.air1703.procare.loaders;

import hr.air1703.core.poziv.RazloziDataLoadedListener;
import hr.air1703.core.poziv.RazloziPozivaDataLoader;
import hr.air1703.database.model.Razlog;

/**
 * Created by pvlahovic on 16.11.2017..
 */

public class RazlogLocalDBDataLoader extends RazloziPozivaDataLoader {

    @Override
    public void loadRazlozi(RazloziDataLoadedListener razloziDataLoadedListener) {
        super.loadRazlozi(razloziDataLoadedListener);

        this.razloziDataLoadedListener.onDataLoaded(Razlog.getAll());
    }
}
