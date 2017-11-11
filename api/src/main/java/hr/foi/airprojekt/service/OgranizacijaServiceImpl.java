package hr.foi.airprojekt.service;

import hr.foi.airprojekt.model.Organizacija;
import hr.foi.airprojekt.model.OrganizacijaSearch;
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
    public List<Organizacija> fetchAllOrganizacija() {
        List<Organizacija> organizacije = new ArrayList<>();

        organizacije.addAll(organizacijaRepository.findAll());

        return organizacije;
    }

    @Override
    public List<Organizacija> fetchaAllOrganizacijaBy(OrganizacijaSearch organizacijaSearch) {
        List<Organizacija> organizacije = new ArrayList<>();

        organizacije.addAll(organizacijaRepository.findAll()
                .stream()
                .filter(o -> calculateDistance(o, organizacijaSearch)).collect(Collectors.toList()));

        return organizacije;
    }

    private boolean calculateDistance(Organizacija o, OrganizacijaSearch o1) {
        double distance = sqrt(pow((o.getXKoordinata() -  o1.getXKoordinata()), 2) + pow((o.getYKoordinata() -  o1.getYKoordinata()), 2)) * 100;

        return distance <= o1.getUdaljenost();
    }

}
