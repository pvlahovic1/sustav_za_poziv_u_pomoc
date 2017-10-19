package hr.foi.airprojekt.repository;

import hr.foi.airprojekt.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

    Korisnik findKorisnikByMailAndLozinka(String mail, String lozinka);

}
