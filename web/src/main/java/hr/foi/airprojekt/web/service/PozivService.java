package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.model.wrappers.NesrecaBasicView;
import hr.foi.airprojekt.web.model.wrappers.NesrecaDetailsView;

import java.util.List;

public interface PozivService {

    List<NesrecaBasicView> fetchAllNesrece();
    NesrecaDetailsView fetchNesrecaDetailViewByNesrecaId(int id);

    void makeNesrecaResolved(int id);

}
