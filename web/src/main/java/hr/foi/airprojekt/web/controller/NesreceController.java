package hr.foi.airprojekt.web.controller;


import hr.foi.airprojekt.web.service.PozivService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class NesreceController {

    private final PozivService pozivService;

    @GetMapping("/nesrece")
    public String getNesrece(Model model) {
        model.addAttribute("nesrece", pozivService.fetchAllNesrece());

        return "nesrece";
    }

    @GetMapping("/nesrece/{id}")
    public String getDetailsNesreca(@PathVariable("id") int id, Model model) {
        model.addAttribute("nesreca", pozivService.fetchNesrecaDetailViewByNesrecaId(id));

        return "fragments/nesrece_fragment::nesreca_show_modal_content";
    }

}
