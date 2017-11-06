package hr.foi.airprojekt.service;

import hr.foi.airprojekt.exception.KorisnikCredentialsException;
import hr.foi.airprojekt.model.Korisnik;
import hr.foi.airprojekt.model.Poziv;
import hr.foi.airprojekt.model.PozivWrapper;
import hr.foi.airprojekt.repository.PozivRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PozivServiceImpl implements PozivService {

    private final PozivRepository pozivRepository;
    private final KorisnikService korisnikService;

    @Override
    public void addNewPozivUPomoc(PozivWrapper pozivWrapper) {

        Korisnik korisnik = korisnikService.fetchKorisnikByOib(pozivWrapper.getOib());

        if (korisnik != null) {
            Poziv poziv = new Poziv();
            poziv.setXKoodinata(pozivWrapper.getX());
            poziv.setYKoordinata(pozivWrapper.getY());
            poziv.setVrijemePrimitka(LocalDateTime.now());
            poziv.setKorisnik(korisnik);

            pozivRepository.save(poziv);
        } else {
           throw new KorisnikCredentialsException("User is missing");
        }
    }

}
