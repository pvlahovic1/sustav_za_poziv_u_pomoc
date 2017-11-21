package hr.foi.airprojekt.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/")
    public String getDefault() {
        return "homepage";
    }

    @GetMapping("/login")
    public String getLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication.getAuthorities()
                .stream().anyMatch(ga -> ga.getAuthority().equals("User"));

        String url = "";

        if (isAuthenticated) {
            url = "redirect:/homepage";
        } else {
            url = "login";
        }

        return url;
    }

    @GetMapping("/homepage")
    public String getHomepage() {
        return "homepage";
    }

}
