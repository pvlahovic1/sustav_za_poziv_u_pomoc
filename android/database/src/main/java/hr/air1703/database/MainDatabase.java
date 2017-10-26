package hr.air1703.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Tadija on 26.10.2017..
 */

@Database(name = MainDatabase.NAME, version = MainDatabase.VERSION)
public class MainDatabase {

    public static final String NAME = "lokalna_baza";
    public static final int VERSION = 1;

}
