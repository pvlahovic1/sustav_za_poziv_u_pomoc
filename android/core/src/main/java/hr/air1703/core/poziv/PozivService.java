package hr.air1703.core.poziv;

/**
 * Created by pvlahovic on 20.11.2017..
 */

public abstract class PozivService {

    protected PozivServiceHandler pozivServiceHandler;

    public PozivService(PozivServiceHandler pozivServiceHandler) {
        this.pozivServiceHandler = pozivServiceHandler;
    }

}
