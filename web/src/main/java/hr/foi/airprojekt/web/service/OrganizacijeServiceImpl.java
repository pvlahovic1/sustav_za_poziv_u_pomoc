package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.model.Organizacija;
import hr.foi.airprojekt.web.model.OrganizacijaEditWrapper;
import hr.foi.airprojekt.web.repository.OrganizacijaRepository;
import hr.foi.airprojekt.web.repository.OrganizacijaTipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizacijeServiceImpl implements OrganizacijeService {

    private final OrganizacijaRepository organizacijaRepository;
    private final OrganizacijaTipRepository organizacijaTipRepository;

    @Override
    public List<Organizacija> fetchAllOrganizacija() {
        return organizacijaRepository.findAll();
    }

    @Override
    public Organizacija fetchById(int idOrganizacija) {
        return organizacijaRepository.findByIdOrganizacije(idOrganizacija);
    }

    @Override
    public OrganizacijaEditWrapper fetchEditWrapperById(int idOrganizacija) {
        OrganizacijaEditWrapper organizacijaEditWrapper = new OrganizacijaEditWrapper();
        Organizacija organizacija = fetchById(idOrganizacija);

        organizacijaEditWrapper.setIdOrganizacije(organizacija.getIdOrganizacije());
        organizacijaEditWrapper.setNaziv(organizacija.getNaziv());
        organizacijaEditWrapper.setOpis(organizacija.getOpis());
        organizacijaEditWrapper.setBrojHitnih(organizacija.getBrojHitnih());
        organizacijaEditWrapper.setBrojNehitnih(organizacija.getBrojNehitnih());
        organizacijaEditWrapper.setTipoviOrganizacije(new ArrayList<>(organizacija.getTipoviOrganizacije()));
        organizacijaEditWrapper.setX(organizacija.getXKoordinata());
        organizacijaEditWrapper.setY(organizacija.getYKoordinata());
        organizacijaEditWrapper.setSviTipoviOrganizacija(organizacijaTipRepository.findAll());

        return organizacijaEditWrapper;
    }

    @Override
    public Organizacija updateOrganizacija(OrganizacijaEditWrapper oew) {
        Organizacija o = organizacijaRepository.findByIdOrganizacije(oew.getIdOrganizacije());

        o.setBrojHitnih(oew.getBrojHitnih());
        o.setBrojNehitnih(oew.getBrojNehitnih());
        o.setNaziv(oew.getNaziv());
        o.setXKoordinata(oew.getX());
        o.setYKoordinata(oew.getY());
        o.setOpis(oew.getOpis());
        o.setTipoviOrganizacije(organizacijaTipRepository.findByIdTipOrganizacijaIn(oew.getOdabraniTipoviOrganizacije()));

        organizacijaRepository.save(o);

        return o;
    }

}
