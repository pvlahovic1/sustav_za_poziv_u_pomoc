package hr.foi.airprojekt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "korisnik", schema = "air_projekt")
public class Korisnik {

    @Id
    @Column(name = "id_korisnik")
    @GeneratedValue
    private Integer idKorisnik;

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

    @Column(name = "message_token")
    private String messageToken;

}
