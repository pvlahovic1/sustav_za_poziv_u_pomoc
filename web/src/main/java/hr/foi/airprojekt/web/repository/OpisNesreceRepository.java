package hr.foi.airprojekt.web.repository;

import hr.foi.airprojekt.web.model.OpisNesrece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OpisNesreceRepository extends JpaRepository<OpisNesrece, Integer> {

    Set<OpisNesrece> findAllByIdIn(List<Integer> ids);

}
