package hr.foi.airprojekt.web.repository;

import hr.foi.airprojekt.web.model.Dispatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatcherRepository extends JpaRepository<Dispatcher, Integer> {

    Dispatcher findByUsername(String username);

}
