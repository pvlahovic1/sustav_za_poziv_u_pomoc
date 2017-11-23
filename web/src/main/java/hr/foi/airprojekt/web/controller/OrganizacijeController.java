package hr.foi.airprojekt.web.controller;

import hr.foi.airprojekt.web.service.OrganizacijeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrganizacijeController {

    private final OrganizacijeService organizacijeService;

    @GetMapping("/organizacije")
    public String getOrganizacijeView(Model model) {
        model.addAttribute("organizacije", organizacijeService.fetchAllOrganizacija());

        return "organizacije";
    }

}
