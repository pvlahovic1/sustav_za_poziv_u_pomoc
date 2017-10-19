package hr.foi.airprojekt.service;

import hr.foi.airprojekt.model.Korisnik;

public interface KorisnikService {

    Korisnik fetchKorisnikByMailAndLozinka(String mail, String lozinka);
    void registerNewKorisnik(Korisnik korisnik);

}
