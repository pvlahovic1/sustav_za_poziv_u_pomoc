package hr.foi.airprojekt.controller.rest;


import hr.foi.airprojekt.exception.KorisnikCredentialsException;
import hr.foi.airprojekt.model.PozivWrapper;
import hr.foi.airprojekt.service.PozivService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-api/poziv")
public class PozivRestController {

    private final PozivService pozivService;

    @PostMapping
    public void callHelp(HttpServletResponse httpServletResponse, @RequestBody PozivWrapper poziv) throws IOException {
        log.info("POST /rest-api/poziv");

        try {
            pozivService.addNewPozivUPomoc(poziv);
        } catch (KorisnikCredentialsException e) {
            log.error(e.getMessage());
            httpServletResponse.sendError(400, e.getLocalizedMessage());
        }
    }

}
