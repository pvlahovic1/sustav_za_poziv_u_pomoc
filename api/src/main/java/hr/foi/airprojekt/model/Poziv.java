package hr.foi.airprojekt.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "poziv", schema = "air_projekt")
@Data
public class Poziv {

    @Id
    @Column(name = "id_poziv")
    @GeneratedValue
    private Integer idPoziv;

    @Column(name = "x_koordinata")
    private double xKoodinata;

    @Column(name = "y_koordinata")
    private double yKoordinata;

    @Column(name = "vrijeme_primitka")
    private LocalDateTime vrijemePrimitka;

    @Column(name = "vrijeme_rjesavanja")
    private LocalDateTime vrijemeRjesavanja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

}
