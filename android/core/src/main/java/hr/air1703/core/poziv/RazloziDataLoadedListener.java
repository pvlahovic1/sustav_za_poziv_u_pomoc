package hr.air1703.core.poziv;

import java.util.List;

import hr.air1703.database.model.Razlog;

/**
 * Created by pvlahovic on 16.11.2017..
 */

public interface RazloziDataLoadedListener {

    void onRazloziPozivaDataLoaded(List<Razlog> organizacije);
    void onRazloziPozivaFailure(int messageCode);

}
