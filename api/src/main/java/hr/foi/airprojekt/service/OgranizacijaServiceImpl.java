package hr.foi.airprojekt.service;

import hr.foi.airprojekt.model.Organizacija;
import hr.foi.airprojekt.model.wrapper.OranizacijaTipWrapper;
import hr.foi.airprojekt.model.wrapper.OrganizacijaSearch;
import hr.foi.airprojekt.model.wrapper.OrganizacijaWrapper;
import hr.foi.airprojekt.repository.OrganizacijaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Service
@RequiredArgsConstructor
public class OgranizacijaServiceImpl implements OrganizacijaService {

    private final OrganizacijaRepository organizacijaRepository;

    @Override
    public List<OrganizacijaWrapper> fetchAllOrganizacija() {
        List<OrganizacijaWrapper> organizacijeWraper = new ArrayList<>();

        List<Organizacija> organizacije = organizacijaRepository.findAll();

        organizacije.forEach(o -> organizacijeWraper.add(convertToOrganizacijaWrapper(o)));

        return organizacijeWraper;
    }

    @Override
    public List<OrganizacijaWrapper> fetchaAllOrganizacijaBy(OrganizacijaSearch organizacijaSearch) {
        List<OrganizacijaWrapper> organizacijeWraper = new ArrayList<>();

        List<Organizacija> organizacije = organizacijaRepository.findAll()
                .stream()
                .filter(o -> calculateDistance(o, organizacijaSearch)).collect(Collectors.toList());



        return organizacijeWraper;
    }

    private OrganizacijaWrapper convertToOrganizacijaWrapper(Organizacija o) {
        OrganizacijaWrapper ow = new OrganizacijaWrapper();
        ow.setNaziv(o.getNaziv());
        ow.setOpis(o.getOpis());
        ow.setBrojHitnih(o.getBrojHitnih());
        ow.setBrojNehitnih(o.getBrojNehitnih());
        ow.setXKoordinata(o.getXKoordinata());
        ow.setYKoordinata(o.getYKoordinata());

        o.getTipOrganizacije().forEach(t -> {
            OranizacijaTipWrapper tw = new OranizacijaTipWrapper();
            tw.setNaziv(t.getNaziv());
            tw.setSlikaURL(t.getUrlSlika());
            ow.getTipOrganizacijeList().add(tw);
        });

        return ow;
    }

    private boolean calculateDistance(Organizacija o, OrganizacijaSearch o1) {
        double distance = sqrt(pow((o.getXKoordinata() -  o1.getXKoordinata()), 2) + pow((o.getYKoordinata() -  o1.getYKoordinata()), 2)) * 100;

        return distance <= o1.getUdaljenost();
    }

}
