package hr.air1703.procare.poziv;

import hr.air1703.core.poziv.PozivServiceHandler;
import hr.air1703.database.model.Razlog;

/**
 * Created by pvlahovic on 3.12.2017..
 */

public class PozivShakerService extends PozivService {

    public PozivShakerService(PozivServiceHandler pozivServiceHandler) {
        super(pozivServiceHandler);
    }

    public void shakerCallHelp(Razlog razlog) {
        callHelp(razlog);
    }

}
