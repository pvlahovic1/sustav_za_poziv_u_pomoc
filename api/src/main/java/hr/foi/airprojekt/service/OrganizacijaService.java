package hr.foi.airprojekt.service;

import hr.foi.airprojekt.model.Organizacija;
import hr.foi.airprojekt.model.OrganizacijaSearch;

import java.util.List;

public interface OrganizacijaService {

    List<Organizacija> fetchAllOrganizacija();
    List<Organizacija> fetchaAllOrganizacijaBy(OrganizacijaSearch organizacijaSearch);

}
