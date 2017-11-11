package hr.foi.airprojekt.controller.rest;


import hr.foi.airprojekt.model.Organizacija;
import hr.foi.airprojekt.model.OrganizacijaSearch;
import hr.foi.airprojekt.service.OrganizacijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-api/ogranizacija")
public class OrganizacijaRestController {

    private final OrganizacijaService organizacijaService;

    @GetMapping
    public List<Organizacija> provideAllOrganizacija() {
        return organizacijaService.fetchAllOrganizacija();
    }

    @PostMapping
    public List<Organizacija> provideOrganizacijaCloseToMe(@RequestBody OrganizacijaSearch organizacijaSearch) {
        return organizacijaService.fetchaAllOrganizacijaBy(organizacijaSearch);
    }

}
