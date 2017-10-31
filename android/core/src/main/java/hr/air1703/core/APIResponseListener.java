package hr.air1703.core;

import hr.air1703.database.model.Korisnik;

/**
 * Created by pvlahovic on 31.10.2017..
 */

public interface APIResponseListener {

    void onLoginSucceeded(Korisnik korisnik);
    void onError(int messageCode);

}
