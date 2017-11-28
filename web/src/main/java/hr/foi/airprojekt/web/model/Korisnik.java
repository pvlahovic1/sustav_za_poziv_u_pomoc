package hr.foi.airprojekt.web.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "korisnik", schema = "air_projekt")
public class Korisnik {

    @Id
    @Column(name = "id_korisnik")
    @GeneratedValue
    private int idKorisnik;

    @Column(name = "oib")
    private String oib;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "mail")
    private String mail;

    @Column(name = "lozinka")
    private String lozinka;

    @Column(name = "broj_mob")
    private String brojMob;

    @OneToMany(mappedBy = "korisnik")
    List<Poziv> pozivi;

}
