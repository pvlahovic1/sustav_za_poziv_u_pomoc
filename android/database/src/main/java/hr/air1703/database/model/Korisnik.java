package hr.air1703.database.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import hr.air1703.database.MainDatabase;

/**
 * Created by Tadija on 26.10.2017..
 */
@Table(database = MainDatabase.class)
public class Korisnik extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    private int idKorisnik;
    @Column
    private String oib;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column
    private String adresa;
    @Column
    private String mail;
    @Column
    private String lozinka;
    @Column
    private String brojMob;

    public Korisnik() {
    }

    public Korisnik(int idKorisnik, String oib, String ime, String prezime, String adresa, String mail, String lozinka, String brojMob) {
        this.idKorisnik = idKorisnik;
        this.oib = oib;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.mail = mail;
        this.lozinka = lozinka;
        this.brojMob = brojMob;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getBrojMob() {
        return brojMob;
    }

    public void setBrojMob(String brojMob) {
        this.brojMob = brojMob;
    }

    public static List<Korisnik> getAll(){
        return SQLite.select().from(Korisnik.class).queryList();
    }

}
