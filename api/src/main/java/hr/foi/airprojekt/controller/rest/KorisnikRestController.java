package hr.foi.airprojekt.controller.rest;

import hr.foi.airprojekt.exception.KorisnikCredentialsException;
import hr.foi.airprojekt.model.Korisnik;
import hr.foi.airprojekt.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
@RequestMapping("/rest-api/korisnik")
public class KorisnikRestController {

    private final KorisnikService korisnikService;

    @PostMapping("/login")
    public Korisnik provideKorisnik(HttpServletResponse httpServletResponse, @RequestBody Map<String, String> map) throws IOException {
        log.info("POST /rest-api/korisnik/login");
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

    @PostMapping()
    public Korisnik registerNewKorisnik(HttpServletResponse httpServletResponse, @RequestBody Korisnik korisnik) throws IOException {
        log.info("POST /rest-api/korisnik");
        Korisnik registriraniKorisnik = null;

        try {
            registriraniKorisnik = korisnikService.registerNewKorisnik(korisnik);
        } catch (KorisnikCredentialsException e) {
            log.error(e.getMessage(), e);
            httpServletResponse.sendError(400, e.getMessage());
        }

        return registriraniKorisnik;
    }

    @PostMapping("/update")
    public Korisnik updateKorisnik(HttpServletResponse httpServletResponse, @RequestBody Korisnik korisnikUpdate) throws IOException {
        log.info("POST /rest-api/korisnik/update");
        Korisnik postojeciKorisnik = null;
        try {
            postojeciKorisnik = korisnikService.fetchKorisnikByOib(korisnikUpdate.getOib());
            postojeciKorisnik = korisnikService.updateKorisnik(postojeciKorisnik, korisnikUpdate);
        } catch (KorisnikCredentialsException e) {
            log.error(e.getMessage(), e);
            httpServletResponse.sendError(400, e.getMessage());
        }

        return postojeciKorisnik;
    }
}

