package hr.foi.airprojekt.controller.rest;

import hr.foi.airprojekt.exception.KorisnikCredentialsException;
import hr.foi.airprojekt.model.Korisnik;
import hr.foi.airprojekt.service.KorisnikService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/rest-api")
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/korisnik/login")
    public Korisnik provideKorisnik(HttpServletResponse httpServletResponse, @RequestBody Map<String, String> map) throws IOException {
        log.trace("POST /rest-api/korisnik/login");
        String mail = map.get("mail");
        String lozinka = map.get("lozinka");
        Korisnik korisnik = null;

        if (!StringUtils.isEmpty(mail) && !StringUtils.isEmpty(lozinka)) {
            try {
                korisnik = korisnikService.fetchKorisnikByMailAndLozinka(mail, lozinka);
            } catch (IllegalArgumentException e ) {
                log.error("Invalid Username = {} or Password = {}", mail, lozinka);
                httpServletResponse.sendError(400, e.getLocalizedMessage());
            }
        } else {
            log.error("Bad request parameters mail={} lozinka= {}", mail, lozinka);
            httpServletResponse.sendError(400, "Bad request parameters [mail]=" + mail + " [lozinka]=" + lozinka);
        }

        return korisnik;
    }

    @PostMapping("/korisnik")
    public void registerNewKorisnik(HttpServletResponse httpServletResponse, @RequestBody Korisnik korisnik) throws IOException {
        log.trace("POST /rest-api/korisnik");
        try {
            korisnikService.registerNewKorisnik(korisnik);
        } catch (KorisnikCredentialsException e) {
            log.error(e.getMessage(), e);
            httpServletResponse.sendError(400, e.getMessage());
        }
    }
}

