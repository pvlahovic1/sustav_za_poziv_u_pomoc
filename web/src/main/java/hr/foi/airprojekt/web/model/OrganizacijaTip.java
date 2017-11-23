package hr.foi.airprojekt.web.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tip_organizacije", schema = "air_projekt")
public class OrganizacijaTip {

    @Id
    @Column(name = "id_tip_organizacija")
    @GeneratedValue
    private Integer idTipOrganizacija;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "url_slika")
    private String urlSlika;

}
