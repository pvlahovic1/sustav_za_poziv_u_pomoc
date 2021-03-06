package hr.foi.airprojekt.web.controller;

import hr.foi.airprojekt.web.model.Organizacija;
import hr.foi.airprojekt.web.model.wrappers.OrganizacijaEditWrapper;
import hr.foi.airprojekt.web.service.OrganizacijeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class OrganizacijeController {

    private final OrganizacijeService organizacijeService;

    @GetMapping("/organizacije")
    public String getOrganizacijeView(Model model) {
        model.addAttribute("organizacije", organizacijeService.fetchAllOrganizacija());

        return "organizacije";
    }

    @GetMapping("/organizacije/table")
    public String getOrganizacijeTable(Model model) {
        model.addAttribute("organizacije", organizacijeService.fetchAllOrganizacija());

        return "fragments/organizacije_fragment::organizacije_table";
    }

    @GetMapping("/organizacije/{id}")
    public String getOrganizacijaById(@PathVariable("id") int idOrganizacija, Model model) {
        model.addAttribute("org", organizacijeService.fetchById(idOrganizacija));

        return "fragments/organizacije_fragment::organizacije_show_modal_content";
    }

    @GetMapping("/organizacije/edit/{id}")
    public String getOrganizacijaEdit(@PathVariable("id") int idOrganizacija, Model model) {
        model.addAttribute("org", organizacijeService.fetchEditWrapperById(idOrganizacija));

        return "fragments/organizacije_fragment::organizacije_edit_modal_content";
    }

    @PostMapping("/organizacije/update")
    public String updateOrganizacija(HttpServletRequest httpServletRequest, @RequestBody OrganizacijaEditWrapper organizacijaEditWrapper, Model model) {
        Organizacija o = organizacijeService.updateOrganizacija(organizacijaEditWrapper);

        model.addAttribute("org", o);

        return "fragments/organizacije_fragment::organizacije_show_modal_content";
    }

    @GetMapping("/organizacije/delete/{id}")
    @ResponseBody
    public void deleteOrganizacija(@PathVariable("id") int idOrganizacija) {
        organizacijeService.deleteOrganzacija(idOrganizacija);
    }

    @GetMapping("/organizacije/new")
    public String createNewOrganizacija(Model model) {
        model.addAttribute("org", organizacijeService.createNewOrganizacijaWrapper());

        return "fragments/organizacije_fragment::organizacije_new_modal_content";
    }

    @PostMapping("/organizacije/new/save")
    @ResponseBody
    public void saveNewOrganizacija(@RequestBody OrganizacijaEditWrapper organizacijaEditWrapper) {
        organizacijeService.saveNewOrganizacija(organizacijaEditWrapper);
    }

}
