package hr.air1703.database.settings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

import hr.air1703.database.MainDatabase;

/**
 * Created by pvlahovic on 12.11.2017..
 */

@Table(database = MainDatabase.class)
public class LocalApplicationLog extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    @SerializedName("idSetting")
    @Expose
    private int idSetting;

    @Column
    private Date vrijemeDohvacanjaOrganizacija;

    @Column
    private Date vrijemeDohvacanjaRazlogaPoziva;

    @Column
    private Date vrijemeSlanjaPozivaUPomoc;

    public int getIdSetting() {
        return idSetting;
    }

    public void setIdSetting(int idSetting) {
        this.idSetting = idSetting;
    }

    public Date getVrijemeDohvacanjaOrganizacija() {
        return vrijemeDohvacanjaOrganizacija;
    }

    public void setVrijemeDohvacanjaOrganizacija(Date vrijemeDohvacanjaOrganizacija) {
        this.vrijemeDohvacanjaOrganizacija = vrijemeDohvacanjaOrganizacija;
    }

    public Date getVrijemeDohvacanjaRazlogaPoziva() {
        return vrijemeDohvacanjaRazlogaPoziva;
    }

    public void setVrijemeDohvacanjaRazlogaPoziva(Date vrijemeDohvacanjaRazlogaPoziva) {
        this.vrijemeDohvacanjaRazlogaPoziva = vrijemeDohvacanjaRazlogaPoziva;
    }

    public Date getVrijemeSlanjaPozivaUPomoc() {
        return vrijemeSlanjaPozivaUPomoc;
    }

    public void setVrijemeSlanjaPozivaUPomoc(Date vrijemeSlanjaPozivaUPomoc) {
        this.vrijemeSlanjaPozivaUPomoc = vrijemeSlanjaPozivaUPomoc;
    }

    public static List<LocalApplicationLog> getAll() {
        return SQLite.select().from(LocalApplicationLog.class).flowQueryList();
    }

}
