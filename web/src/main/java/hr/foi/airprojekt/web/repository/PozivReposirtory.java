package hr.foi.airprojekt.web.repository;

import hr.foi.airprojekt.web.model.Poziv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PozivReposirtory extends JpaRepository<Poziv, Integer> {

    List<Poziv> findAllByVrijemeRjesavanjaIsNull();

}
