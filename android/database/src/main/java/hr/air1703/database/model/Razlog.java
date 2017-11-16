package hr.air1703.database.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import hr.air1703.database.MainDatabase;

/**
 * Created by pvlahovic on 16.11.2017..
 */

@Table(database = MainDatabase.class)
public class Razlog extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    @SerializedName("id")
    @Expose
    private int id;

    @Column
    @SerializedName("naziv")
    @Expose
    private String naziv;

    public Razlog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public static List<Razlog> getAll() {
        return new Select().from(Razlog.class).queryList();
    }

    public static void deleteAll() {
        Delete.table(Razlog.class);
    }

    @Override
    public String toString() {
        return this.naziv;
    }
}
