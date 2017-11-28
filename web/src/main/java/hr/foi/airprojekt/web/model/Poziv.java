package hr.foi.airprojekt.web.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "poziv", schema = "air_projekt")
public class Poziv {

    @Id
    @Column(name = "id_poziv")
    @GeneratedValue
    private int idPoziv;

    @Column(name = "x_koordinata")
    private double xKoordinata;

    @Column(name = "y_koordinata")
    private double yKoordinata;

    @Column(name = "vrijeme_rjesavanja")
    private LocalDateTime vrijemeRjesavanja;

    @Column(name = "vrijeme_primitka")
    private LocalDateTime vrijemePrimitka;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="korisnik_id")
    private Korisnik korisnik;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "poziv_opis_nesrece",
            joinColumns = { @JoinColumn(name = "id_poziv") },
            inverseJoinColumns = { @JoinColumn(name = "id_opis_nesrece") }
    )
    Set<OpisNesrece> opisiNesrece = new HashSet<>();

}
