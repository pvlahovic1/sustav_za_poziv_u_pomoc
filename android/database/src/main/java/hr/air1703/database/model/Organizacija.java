package hr.air1703.database.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import hr.air1703.database.MainDatabase;

/**
 * Created by Tadija on 7.11.2017..
 */


@Table(database = MainDatabase.class)
public class Organizacija extends BaseModel {
    @PrimaryKey(autoincrement = true)
    @Column
    @SerializedName("idOrganizacija")
    @Expose
    private int idOrganizacija;
    @Column
    @SerializedName("naziv")
    @Expose
    private String naziv;
    @Column
    @SerializedName("opis")
    @Expose
    private String opis;
    @Column
    @SerializedName("brojHitnih")
    @Expose
    private int brojHitnih;
    @Column
    @SerializedName("brojNehitnih")
    @Expose
    private int brojNehitnih;
    @Column
    @SerializedName("x_koordinata")
    @Expose
    private double x_koordinata;
    @Column
    @SerializedName("y_koordinata")
    @Expose
    private double y_koordinata;



    public Organizacija() {
    }

    public Organizacija(int idOrganizacija, String naziv, String opis, int brojHitnih, int brojNehitnih, double x_koordinata, double y_koordinata) {
        this.idOrganizacija = idOrganizacija;
        this.naziv = naziv;
        this.opis = opis;
        this.brojHitnih = brojHitnih;
        this.brojNehitnih = brojNehitnih;
        this.x_koordinata = x_koordinata;
        this.y_koordinata = y_koordinata;
    }

    public int getIdOrganizacija() {
        return idOrganizacija;
    }

    public void setIdOrganizacija(int idOrganizacija) {
        this.idOrganizacija = idOrganizacija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBrojHitnih() {
        return brojHitnih;
    }

    public void setBrojHitnih(int brojHitnih) {
        this.brojHitnih = brojHitnih;
    }

    public int getBrojNehitnih() {
        return brojNehitnih;
    }

    public void setBrojNehitnih(int brojNehitnih) {
        this.brojNehitnih = brojNehitnih;
    }

    public double getX_koordinata() {
        return x_koordinata;
    }

    public void setX_koordinata(double x_koordinata) {
        this.x_koordinata = x_koordinata;
    }

    public double getY_koordinata() {
        return y_koordinata;
    }

    public void setY_koordinata(double y_koordinata) {
        this.y_koordinata = y_koordinata;
    }

    public static List<Organizacija> getAll(){
        return SQLite.select().from(Organizacija.class).queryList();
    }

    public List<TipOrganizacije> getTipOrganizacijeList(){
        return new Select().from(TipOrganizacije.class)
                .where(TipOrganizacije_Table.organizacijaId.eq(idOrganizacija))
                .queryList();
    }

    public static void deleteAll() {
        Delete.table(Organizacija.class);
    }


    @Override
    public String toString() {
        return idOrganizacija + " " + naziv + " " + x_koordinata + " " + y_koordinata;
    }
}