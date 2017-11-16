package hr.air1703.core.poziv;

/**
 * Created by pvlahovic on 16.11.2017..
 */

public interface PozivResponseListener {

    void onPosivSucceeded();
    void onPozivFailure(int messageCode);

}
