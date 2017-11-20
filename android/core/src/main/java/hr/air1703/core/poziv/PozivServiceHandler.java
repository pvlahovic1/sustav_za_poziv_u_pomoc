package hr.air1703.core.poziv;

/**
 * Created by pvlahovic on 20.11.2017..
 */

public interface PozivServiceHandler {

    void onTimeDifferenceProblem(long timeDiff);
    void onFiveSecondRule();
    void onPozivSucceeded();
    void onPozivFailure(int messageCode);

}
