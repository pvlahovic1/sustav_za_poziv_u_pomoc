package hr.foi.airprojekt.web.model;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "organizacija_hitni_prijem", schema = "air_projekt")
public class Organizacija {

    @Id
    @GeneratedValue
    @Column(name = "id_organizacija")
    private Integer idOrganizacije;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "x_koordinata")
    private String xKoordinata;

    @Column(name = "y_koordinata")
    private String yKoordinata;

    @Column(name = "opis")
    private String opis;

    @Column(name = "broj_hitnih")
    private int brojHitnih;

    @Column(name = "broj_nehitnih")
    private int brojNehitnih;

}
