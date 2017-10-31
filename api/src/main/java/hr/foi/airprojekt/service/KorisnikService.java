package hr.foi.airprojekt.service;

import hr.foi.airprojekt.model.Korisnik;

public interface KorisnikService {

    Korisnik fetchKorisnikByMailAndLozinka(String mail, String lozinka);
    Korisnik fetchKorisnikByOib(String oib);
    Korisnik updateKorisnik(Korisnik stariKorisnik, Korisnik noviKorisnik);
    Korisnik registerNewKorisnik(Korisnik korisnik);

}
