package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.model.Organizacija;
import hr.foi.airprojekt.web.repository.OrganizacijaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizacijeServiceImpl implements OrganizacijeService {

    private final OrganizacijaRepository organizacijaRepository;

    @Override
    public List<Organizacija> fetchAllOrganizacija() {
        return organizacijaRepository.findAll();
    }

}
