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
public class OrganizacijaTipOrganizacije extends BaseModel{

    @PrimaryKey(autoincrement = true)
    @Column
    @SerializedName("idOrganizacija_TipOrganizacije")
    private int idOrganizacija_TipOrganizacije;

    @Column
    @SerializedName("idOrganizacije")
    @Expose
    private int idOrganizacije;

    @Column
    @SerializedName("idTipOrganizacije")
    @Expose
    private int idTipOrganizacije;

    @Column
    @ForeignKey(tableClass = Organizacija.class)
    Organizacija organizacija;

    @Column
    @ForeignKey(tableClass = TipOrganizacije.class)
    TipOrganizacije tipOrganizacije;

    public OrganizacijaTipOrganizacije() {
    }

    public OrganizacijaTipOrganizacije(int idOrganizacija_TipOrganizacije, int idOrganizacije, int idTipOrganizacije, Organizacija organizacija, TipOrganizacije tipOrganizacije) {
        this.idOrganizacija_TipOrganizacije = idOrganizacija_TipOrganizacije;
        this.idOrganizacije = idOrganizacije;
        this.idTipOrganizacije = idTipOrganizacije;
        this.organizacija = organizacija;
        this.tipOrganizacije = tipOrganizacije;
    }

    public int getIdOrganizacija_TipOrganizacije() {
        return idOrganizacija_TipOrganizacije;
    }

    public void setIdOrganizacija_TipOrganizacije(int idOrganizacija_TipOrganizacije) {
        this.idOrganizacija_TipOrganizacije = idOrganizacija_TipOrganizacije;
    }

    public int getIdOrganizacije() {
        return idOrganizacije;
    }

    public void setIdOrganizacije(int idOrganizacije) {
        this.idOrganizacije = idOrganizacije;
    }

    public int getIdTipOrganizacije() {
        return idTipOrganizacije;
    }

    public void setIdTipOrganizacije(int idTipOrganizacije) {
        this.idTipOrganizacije = idTipOrganizacije;
    }

    public Organizacija getOrganizacija() {
        return organizacija;
    }

    public void setOrganizacija(Organizacija organizacija) {
        this.organizacija = organizacija;
    }

    public TipOrganizacije getTipOrganizacije() {
        return tipOrganizacije;
    }

    public void setTipOrganizacije(TipOrganizacije tipOrganizacije) {
        this.tipOrganizacije = tipOrganizacije;
    }
}
