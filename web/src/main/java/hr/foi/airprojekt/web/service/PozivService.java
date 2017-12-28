package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.model.wrappers.NesrecaBasicView;
import hr.foi.airprojekt.web.model.wrappers.NesrecaDetailsView;
import hr.foi.airprojekt.web.model.wrappers.NesrecaEditDto;
import hr.foi.airprojekt.web.model.wrappers.NesrecaEditView;

import java.util.List;

public interface PozivService {

    List<NesrecaBasicView> fetchAllNesrece();
    NesrecaDetailsView fetchNesrecaDetailViewByNesrecaId(int id);
    NesrecaDetailsView updateNesreca(NesrecaEditDto nesrecaEditDto);
    NesrecaEditView fetchEditWrapper(int id);
    void makeNesrecaResolved(int id);
    String sendPushNotification(int idNesrece);

}
