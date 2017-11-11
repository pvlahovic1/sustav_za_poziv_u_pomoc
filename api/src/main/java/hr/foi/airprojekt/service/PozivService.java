package hr.foi.airprojekt.service;

import hr.foi.airprojekt.model.wrapper.OpisNesreceWrapper;
import hr.foi.airprojekt.model.wrapper.PozivWrapper;

import java.util.List;

public interface PozivService {

    void addNewPozivUPomoc(PozivWrapper pozivWrapper);
    List<OpisNesreceWrapper> fetchAllReasons();

}
