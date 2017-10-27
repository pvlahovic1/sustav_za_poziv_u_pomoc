package hr.foi.airprojekt.service;

import hr.foi.airprojekt.exception.KorisnikCredentialsException;
import hr.foi.airprojekt.model.Korisnik;
import hr.foi.airprojekt.repository.KorisnikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl implements KorisnikService {

    private final KorisnikRepository korisnikRepository;

    @Override
    public Korisnik fetchKorisnikByMailAndLozinka(String mail, String lozinka) {
        Korisnik korisnik = korisnikRepository.findKorisnikByMailAndLozinka(mail, lozinka);

        if (korisnik == null) {
            throw new IllegalArgumentException("Invalid Username or Password");
        }

        return korisnik;
    }

    @Override
    public void registerNewKorisnik(Korisnik korisnik) {
        try {
            korisnikRepository.save(korisnik);
        } catch (Exception e) {
            String errorMessage;
            if (e.getMessage().contains("oib_UNIQUE")) {
                errorMessage = "OIB already exists";
            } else if (e.getMessage().contains("mail_UNIQUE")) {
                errorMessage = "mail already exists";
            } else {
                errorMessage = e.getMessage();
            }
            throw new KorisnikCredentialsException(errorMessage);
        }
    }
}
