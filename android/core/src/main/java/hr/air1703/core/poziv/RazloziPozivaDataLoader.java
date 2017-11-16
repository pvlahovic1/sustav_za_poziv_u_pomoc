package hr.air1703.core.poziv;

/**
 * Created by pvlahovic on 16.11.2017..
 */

public abstract class RazloziPozivaDataLoader {

    protected RazloziDataLoadedListener razloziDataLoadedListener;

    public void loadRazlozi(RazloziDataLoadedListener razloziDataLoadedListener) {
        this.razloziDataLoadedListener = razloziDataLoadedListener;
    }
}
