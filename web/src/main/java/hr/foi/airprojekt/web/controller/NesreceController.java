package hr.foi.airprojekt.web.controller;


import hr.foi.airprojekt.web.model.wrappers.NesrecaEditDto;
import hr.foi.airprojekt.web.service.PozivService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/nesrece/edit/{id}")
    public String getEditFormNesreca(@PathVariable("id") int id, Model model) {
        model.addAttribute("nesreca", pozivService.fetchEditWrapper(id));

        return "fragments/nesrece_fragment::nesreca_edit_modal_content";
    }

    @PostMapping("nesrece/update")
    public String updateNesreca(@RequestBody NesrecaEditDto dto, Model model) {
        model.addAttribute("nesreca", pozivService.updateNesreca(dto));

        return "fragments/nesrece_fragment::nesreca_show_modal_content";
    }

    @GetMapping("/nesrece/rijeseno/{id}")
    @ResponseBody
    public void setAsResolved(@PathVariable("id") int id) {
        pozivService.makeNesrecaResolved(id);
    }

    @GetMapping("/nesrece/table")
    public String getNesreceTable(Model model) {
        model.addAttribute("nesrece", pozivService.fetchAllNesrece());

        return "fragments/nesrece_fragment::nesreca_table";
    }

}
