package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.model.Organizacija;
import hr.foi.airprojekt.web.model.wrappers.OrganizacijaEditWrapper;

import java.util.List;

public interface OrganizacijeService {

    List<Organizacija> fetchAllOrganizacija();
    Organizacija fetchById(int idOrganizacija);
    OrganizacijaEditWrapper fetchEditWrapperById(int idOrganizacija);
    OrganizacijaEditWrapper createNewOrganizacijaWrapper();

    Organizacija updateOrganizacija(OrganizacijaEditWrapper oew);
    void deleteOrganzacija(int idOrganizacija);
    void saveNewOrganizacija(OrganizacijaEditWrapper oew);

}
