package hr.air1703.database.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("idKorisnik")
    @Expose
    private Integer idKorisnik;
    @SerializedName("oib")
    @Expose
    private String oib;
    @SerializedName("ime")
    @Expose
    private String ime;
    @SerializedName("prezime")
    @Expose
    private String prezime;
    @SerializedName("adresa")
    @Expose
    private String adresa;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("lozinka")
    @Expose
    private String lozinka;
    @SerializedName("brojMob")
    @Expose
    private String brojMob;

    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Integer idKorisnik) {
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

    public String toLog(){
        return new String(" Account: idKorisnik: " + idKorisnik +
                        ", oib: " + oib +
                        ", ime: " + ime +
                        ", prezime: " + prezime +
                        ", adresa: " + adresa +
                        ", mail: " + mail +
                        ", lozinka: " + lozinka +
                        ", brojMob: " + brojMob);
    }

    public Post() {
        this.idKorisnik = -1;
        this.oib = "";
        this.ime = "";
        this.prezime = "";
        this.adresa = "";
        this.mail = "";
        this.lozinka = "";
        this.brojMob = "";
    }



}
