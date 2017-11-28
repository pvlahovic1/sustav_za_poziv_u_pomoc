package hr.foi.airprojekt.web.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "poziv", schema = "air_projekt")
public class Poziv {

    @Id
    @Column(name = "id_poziv")
    @GeneratedValue
    private int idPoziv;

    @Column(name = "x_koordinata")
    private String xKoordinata;

    @Column(name = "y_koordinata")
    private String yKoordinata;

    @Column(name = "vrijeme_rjesavanja")
    private LocalDateTime vrijemeRjesavanja;

    @Column(name = "vrijeme_primitka")
    private LocalDateTime vrijemePrimitka;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="korisnik_id")
    private Korisnik korisnik;

}
