package hr.foi.airprojekt.repository;

import hr.foi.airprojekt.model.Poziv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PozivRepository extends JpaRepository<Poziv, Integer> {

}
