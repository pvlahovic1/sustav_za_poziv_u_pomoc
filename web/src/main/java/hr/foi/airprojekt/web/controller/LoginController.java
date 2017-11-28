package hr.foi.airprojekt.web.controller;


import hr.foi.airprojekt.web.exception.UsernameExistsException;
import hr.foi.airprojekt.web.model.wrappers.User;
import hr.foi.airprojekt.web.service.DispatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final DispatcherService dispatcherService;

    @GetMapping("/")
    public String getDefault() {
        return "nesrece";
    }

    @GetMapping("/login")
    public String getLogin() {
        String url;

        if (isAuthenticated()) {
            url = "redirect:/nesrece";
        } else {
            url = "login";
        }

        return url;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        String url;

        if (isAuthenticated()) {
            url = "redirect:/nesrece";
        } else {
            User user = new User();
            model.addAttribute("user", user);
            url = "register";
        }

        return url;
    }

    @PostMapping("/register")
    public ModelAndView registerNewUser(@ModelAttribute("user") @Valid User user, BindingResult result, Errors errors) {
        if (!result.hasErrors()) {
            try {
                dispatcherService.registerNewDispatcher(user);
            } catch (UsernameExistsException e) {
                result.rejectValue("username", "message.usernameError");
            }
        }

        if (result.hasErrors()) {
            return new ModelAndView("register", "user", user);
        } else {
            return new ModelAndView("login", "user", user);
        }
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream().anyMatch(ga -> ga.getAuthority().equals("User"));
    }

}
