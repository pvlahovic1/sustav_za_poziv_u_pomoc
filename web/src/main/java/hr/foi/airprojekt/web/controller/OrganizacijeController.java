package hr.foi.airprojekt.web.controller;

import hr.foi.airprojekt.web.model.Organizacija;
import hr.foi.airprojekt.web.service.OrganizacijeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class OrganizacijeController {

    private final OrganizacijeService organizacijeService;

    @GetMapping("/organizacije")
    public String getOrganizacijeView(Model model) {
        model.addAttribute("organizacije", organizacijeService.fetchAllOrganizacija());

        return "organizacije";
    }

    @GetMapping("/organizacije/{id}")
    public String getOrganizacijaById(@PathVariable("id") int idOrganizacija, Model model) {
        model.addAttribute("org", organizacijeService.fetchById(idOrganizacija));

        return "fragments/organizacije_fragment::organizacije_show_modal_content";
    }

}
