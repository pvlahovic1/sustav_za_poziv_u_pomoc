package hr.foi.airprojekt.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NesreceController {

    @GetMapping("/nesrece")
    public String getNesrece() {
        return "nesrece";
    }

}
