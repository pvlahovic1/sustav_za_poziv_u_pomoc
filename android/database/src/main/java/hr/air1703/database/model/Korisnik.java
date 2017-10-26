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
    private int id;
    @Column
    private String OIB;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column
    private String adresa;
    @Column
    private String email;
    @Column
    private String lozinka;
    @Column
    private String brojMobitela;

    public Korisnik() {
    }

    public Korisnik(int id, String OIB, String ime, String prezime, String adresa, String email, String lozinka, String brojMobitela) {
        this.id = id;
        this.OIB = OIB;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.email = email;
        this.lozinka = lozinka;
        this.brojMobitela = brojMobitela;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOIB() {
        return OIB;
    }

    public void setOIB(String OIB) {
        this.OIB = OIB;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getBrojMobitela() {
        return brojMobitela;
    }

    public void setBrojMobitela(String brojMobitela) {
        this.brojMobitela = brojMobitela;
    }

    public static List<Korisnik> getAll(){
        return SQLite.select().from(Korisnik.class).queryList();
    }

}
