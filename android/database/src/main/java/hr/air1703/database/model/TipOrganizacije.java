package hr.air1703.database.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import hr.air1703.database.MainDatabase;


/**
 * Created by Tadija on 7.11.2017..
 */


@Table(database = MainDatabase.class)
public class TipOrganizacije extends BaseModel{
    @PrimaryKey(autoincrement = true)
    @Column
    @SerializedName("idTipOrganizacije")
    @Expose
    private int idTipOrganizacije;
    @Column
    @SerializedName("naziv")
    @Expose
    private String naziv;
    @Column
    @SerializedName("slikaURL")
    @Expose
    private String slikaURL;
    @Column
    @SerializedName("organizacijaId")
    @Expose
    private int organizacijaId;

    @Column
    @ForeignKey(tableClass = Organizacija.class)
    Organizacija organizacija;

    
    public TipOrganizacije() {
    }

    public TipOrganizacije(int idTipOrganizacije, String naziv, String slikaURL, int organizacijaId, Organizacija organizacija) {
        this.idTipOrganizacije = idTipOrganizacije;
        this.naziv = naziv;
        this.slikaURL = slikaURL;
        this.organizacijaId = organizacijaId;
        this.organizacija = organizacija;
    }

    public int getIdTipOrganizacije() {
        return idTipOrganizacije;
    }

    public void setIdTipOrganizacije(int idTipOrganizacije) {
        this.idTipOrganizacije = idTipOrganizacije;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSlikaURL() {
        return slikaURL;
    }

    public void setSlikaURL(String slikaURL) {
        this.slikaURL = slikaURL;
    }

    public int getOrganizacijaId() {
        return organizacijaId;
    }

    public void setOrganizacijaId(int organizacijaId) {
        this.organizacijaId = organizacijaId;
    }

    public Organizacija getOrganizacija() {
        return organizacija;
    }

    public void setOrganizacija(Organizacija organizacija) {
        this.organizacija = organizacija;
    }
}