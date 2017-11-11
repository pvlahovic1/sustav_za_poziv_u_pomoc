package hr.foi.airprojekt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "organizacija_hitni_prijem", schema = "air_projekt")
public class Organizacija {

    @Id
    @Column(name = "id_organizacija")
    @GeneratedValue
    private Integer idOrganizacija;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "x_koordinata")
    private double xKoordinata;

    @Column(name = "y_koordinata")
    private double yKoordinata;

    @Column(name = "opis")
    private String opis;

    @Column(name = "broj_hitnih")
    private int brojHitnih;

    @Column(name = "broj_nehitnih")
    private int brojNehitnih;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "organizacija_tip_organizacije",
            joinColumns = { @JoinColumn(name = "id_organizacija") },
            inverseJoinColumns = { @JoinColumn(name = "id_tip_organizacija") }
    )
    Set<OrganizacijaTip> tipOrganizacije = new HashSet<>();

}
