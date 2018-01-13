package hr.air1703.core.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

/**
 * Created by pvlahovic on 13.1.2018..
 */

public class SharedPreferencesWorker {

    private SharedPreferences sharedPreferences;
    private static SharedPreferencesWorker instance;

    private SharedPreferencesWorker(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferencesWorker getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesWorker(context);
        }

        return instance;
    }

    public static SharedPreferencesWorker getInstance() {
        if (instance != null) {
            return instance;
        } else {
            throw new IllegalArgumentException("Should use getInstance(Context) first");
        }
    }

    public Date getVrijemeDohvacanjaOrganizacija() {
        long milis = sharedPreferences.getLong("vrijemeDohvacanjaOrganizacija", 0);
        Date date = new Date();
        date.setTime(milis);

        return date;
    }

    public void setVrijemeDohvacanjaOrganizacija(Date vrijemeDohvacanjaOrganizacija) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("vrijemeDohvacanjaOrganizacija", vrijemeDohvacanjaOrganizacija.getTime());
        editor.apply();
    }

    public Date getVrijemeDohvacanjaRazlogaPoziva() {
        long milis = sharedPreferences.getLong("vrijemeDohvacanjaRazlogaPoziva", 0);
        Date date = new Date();
        date.setTime(milis);

        return date;
    }

    public void setVrijemeDohvacanjaRazlogaPoziva(Date vrijemeDohvacanjaRazlogaPoziva) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("vrijemeDohvacanjaRazlogaPoziva", vrijemeDohvacanjaRazlogaPoziva.getTime());
        editor.apply();
    }

    public Date getVrijemeSlanjaPozivaUPomoc() {
        long milis = sharedPreferences.getLong("vrijemeSlanjaPozivaUPomoc", 0);
        Date date = new Date();
        date.setTime(milis);

        return date;
    }

    public void setVrijemeSlanjaPozivaUPomoc(Date vrijemeSlanjaPozivaUPomoc) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("vrijemeSlanjaPozivaUPomoc", vrijemeSlanjaPozivaUPomoc.getTime());
        editor.apply();
    }

}
