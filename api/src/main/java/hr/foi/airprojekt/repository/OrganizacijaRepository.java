package hr.foi.airprojekt.repository;

import hr.foi.airprojekt.model.Organizacija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizacijaRepository extends JpaRepository<Organizacija, Integer> {
}
