package hr.foi.airprojekt.service;

import hr.foi.airprojekt.model.wrapper.OrganizacijaSearch;
import hr.foi.airprojekt.model.wrapper.OrganizacijaWrapper;

import java.util.List;

public interface OrganizacijaService {

    List<OrganizacijaWrapper> fetchAllOrganizacija();
    List<OrganizacijaWrapper> fetchaAllOrganizacijaBy(OrganizacijaSearch organizacijaSearch);

}
