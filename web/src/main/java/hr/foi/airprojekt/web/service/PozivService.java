package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.model.wrappers.NesreceTableView;

import java.util.List;

public interface PozivService {

    List<NesreceTableView> fetchAllNesrece();

}
