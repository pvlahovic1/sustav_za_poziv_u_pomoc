package hr.foi.airprojekt.web.repository;

import hr.foi.airprojekt.web.model.OrganizacijaTip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrganizacijaTipRepository extends JpaRepository<OrganizacijaTip, Integer> {

    Set<OrganizacijaTip> findByIdTipOrganizacijaIn(List<Integer> ids);

}
