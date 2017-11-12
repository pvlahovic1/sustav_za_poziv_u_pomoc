package hr.foi.airprojekt.controller.rest;


import hr.foi.airprojekt.model.wrapper.OrganizacijaSearch;
import hr.foi.airprojekt.model.wrapper.OrganizacijaWrapper;
import hr.foi.airprojekt.service.OrganizacijaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/rest-api/ogranizacija")
public class OrganizacijaRestController {

    private final OrganizacijaService organizacijaService;

    @GetMapping
    public List<OrganizacijaWrapper> provideAllOrganizacija() {
        log.info("GET /rest-api/ogranizacija");
        return organizacijaService.fetchAllOrganizacija();
    }

    @PostMapping
    public List<OrganizacijaWrapper> provideOrganizacijaCloseToMe(@RequestBody OrganizacijaSearch organizacijaSearch) {
        log.info("POST /rest-api/ogranizacija");
        return organizacijaService.fetchaAllOrganizacijaBy(organizacijaSearch);
    }

}
