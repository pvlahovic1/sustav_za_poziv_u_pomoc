package hr.foi.airprojekt.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/homepage")
    public String getHomepage() {
        return "homepage";
    }

}
