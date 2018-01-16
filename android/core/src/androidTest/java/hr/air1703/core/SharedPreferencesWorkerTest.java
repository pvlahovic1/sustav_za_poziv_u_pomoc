package hr.air1703.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Date;

import hr.air1703.core.sharedpreferences.SharedPreferencesWorker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SharedPreferencesWorkerTest {

    @Mock
    Context context;

    @Mock
    SharedPreferences sharedPreferences;

    @Before
    public void setup() {
        context = mock(Context.class);
        sharedPreferences = mock(SharedPreferences.class);
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences);

        when(sharedPreferences.getLong(eq("vrijemeDohvacanjaOrganizacija"), anyLong())).thenReturn(1516115967319L);
        when(sharedPreferences.getLong(eq("vrijemeDohvacanjaRazlogaPoziva"), anyLong())).thenReturn(1516115967319L);
        when(sharedPreferences.getLong(eq("vrijemeSlanjaPozivaUPomoc"), anyLong())).thenReturn(1516115967319L);
    }

    @Test
    public void returnSharedPreferencesWorker_onGetInstance() throws Exception {
        SharedPreferencesWorker sharedPreferencesWorker = SharedPreferencesWorker.getInstance(context);

        assertNotNull(sharedPreferencesWorker);
    }

    @Test
    public void returnDate_onGetVrijemeDohvacanjaOrganizacija() throws Exception {
        SharedPreferencesWorker sharedPreferencesWorker = SharedPreferencesWorker.getInstance(context);

        Date date = sharedPreferencesWorker.getVrijemeDohvacanjaOrganizacija();

        assertEquals(date.getTime(), 1516115967319L);
    }

    @Test
    public void returnDate_onGetVrijemeDohvacanjaRazlogaPoziva() throws Exception {
        SharedPreferencesWorker sharedPreferencesWorker = SharedPreferencesWorker.getInstance(context);

        Date date = sharedPreferencesWorker.getVrijemeDohvacanjaRazlogaPoziva();

        assertEquals(date.getTime(), 1516115967319L);
    }

    @Test
    public void returnDate_onGetVrijemeSlanjaPozivaUPomoc() throws Exception {
        SharedPreferencesWorker sharedPreferencesWorker = SharedPreferencesWorker.getInstance(context);

        Date date = sharedPreferencesWorker.getVrijemeSlanjaPozivaUPomoc();

        assertEquals(date.getTime(), 1516115967319L);
    }

}
