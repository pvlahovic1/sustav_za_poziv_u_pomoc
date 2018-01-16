package hr.air1703.procare;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import hr.air1703.database.model.Korisnik;
import hr.air1703.procare.login.UserApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by pvlahovic on 16.1.2018..
 */
@RunWith(AndroidJUnit4.class)
public class AccountActivityTest {

    private final static String KORISNIK_PASSWORD = "912C106A14310615DFE86B9B571CBACF77849A6F";
    private final static String KORISNIK_NAME = "imeTest";
    private final static String KORISNIK_SURNAME = "prezimeTest";
    private final static String KORISNIK_MAIL = "mail@mail.com";
    private final static String KORISNIK_OIB = "12345678903";
    private final static String KORISNIK_ADRESS = "Adresa Test";
    private final static String KORISNIK_PHONE = "0987654321";

    private final static String KORISNIK_NEW_PASSWORD_RAW = "newLozinka";
    private final static String KORISNIK_NEW_PASSWORD = "AC427FA97B5EC13DD00B205D571F39D229C5DFC3";
    private final static String KORISNIK_NEW_NAME = "imeTestNew";
    private final static String KORISNIK_NEW_SURNAME = "prezimeTestNew";
    private final static String KORISNIK_NEW_MAIL = "newMail@mail.com";
    private final static String KORISNIK_NEW_ADRESS = "Adresa Test New";
    private final static String KORISNIK_NEW_PHONE = "0987654322";

    private final static String KORISNIK_BAD_VALUE = "";


    private Korisnik korisnik;
    private AccountActivity accountActivity;

    @Rule
    public ActivityTestRule<AccountActivity> rule = new ActivityTestRule<>(AccountActivity.class);

    @Mock
    private UserApi userApi;

    @Before
    public void setup() {
        accountActivity = rule.getActivity();

        userApi = mock(UserApi.class);
        accountActivity.userApi = userApi;

        korisnik = new Korisnik();
        korisnik.setIdKorisnik(1);
        korisnik.setIme(KORISNIK_NAME);
        korisnik.setPrezime(KORISNIK_SURNAME);
        korisnik.setMail(KORISNIK_MAIL);
        korisnik.setOib(KORISNIK_OIB);
        korisnik.setAdresa(KORISNIK_ADRESS);
        korisnik.setBrojMob(KORISNIK_PHONE);
        korisnik.setLozinka(KORISNIK_PASSWORD);

        accountActivity.trenutniKorisnik = korisnik;
    }

    @Test
    @UiThreadTest
    public void updateKorisnikWithNewPassword_onButtonUpdateClicked() {
        accountActivity.editTextIme.setText(KORISNIK_NEW_NAME);
        accountActivity.editTextPrezime.setText(KORISNIK_NEW_SURNAME);
        accountActivity.editTextEmail.setText(KORISNIK_NEW_MAIL);
        accountActivity.textViewOib.setText(KORISNIK_OIB);
        accountActivity.editTextBrojMobitela.setText(KORISNIK_NEW_PHONE);
        accountActivity.editTextAdresa.setText(KORISNIK_NEW_ADRESS);
        accountActivity.editTextLozinka.setText(KORISNIK_NEW_PASSWORD_RAW);

        accountActivity.buttonUpdateClicked();

        assertTrue(korisnik.getIdKorisnik() == 1);
        assertEquals(korisnik.getIme(), KORISNIK_NEW_NAME);
        assertEquals(korisnik.getPrezime(), KORISNIK_NEW_SURNAME);
        assertEquals(korisnik.getMail(), KORISNIK_NEW_MAIL);
        assertEquals(korisnik.getOib(), KORISNIK_OIB);
        assertEquals(korisnik.getAdresa(), KORISNIK_NEW_ADRESS);
        assertEquals(korisnik.getBrojMob(), KORISNIK_NEW_PHONE);
        assertEquals(korisnik.getLozinka(), KORISNIK_NEW_PASSWORD);

        verify(userApi, times(1)).update(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void updateKorisnikWithOldPassword_onButtonUpdateClicked() {
        accountActivity.editTextIme.setText(KORISNIK_NEW_NAME);
        accountActivity.editTextPrezime.setText(KORISNIK_NEW_SURNAME);
        accountActivity.editTextEmail.setText(KORISNIK_NEW_MAIL);
        accountActivity.textViewOib.setText(KORISNIK_OIB);
        accountActivity.editTextBrojMobitela.setText(KORISNIK_NEW_PHONE);
        accountActivity.editTextAdresa.setText(KORISNIK_NEW_ADRESS);

        accountActivity.buttonUpdateClicked();

        assertTrue(korisnik.getIdKorisnik() == 1);
        assertEquals(korisnik.getIme(), KORISNIK_NEW_NAME);
        assertEquals(korisnik.getPrezime(), KORISNIK_NEW_SURNAME);
        assertEquals(korisnik.getMail(), KORISNIK_NEW_MAIL);
        assertEquals(korisnik.getOib(), KORISNIK_OIB);
        assertEquals(korisnik.getAdresa(), KORISNIK_NEW_ADRESS);
        assertEquals(korisnik.getBrojMob(), KORISNIK_NEW_PHONE);
        assertEquals(korisnik.getLozinka(), KORISNIK_PASSWORD);

        verify(userApi, times(1)).update(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void dontUpdateKorisnik_onButtonUpdateClicked_withBadName() {
        accountActivity.editTextIme.setText(KORISNIK_BAD_VALUE);
        accountActivity.editTextPrezime.setText(KORISNIK_NEW_SURNAME);
        accountActivity.editTextEmail.setText(KORISNIK_NEW_MAIL);
        accountActivity.textViewOib.setText(KORISNIK_OIB);
        accountActivity.editTextBrojMobitela.setText(KORISNIK_NEW_PHONE);
        accountActivity.editTextAdresa.setText(KORISNIK_NEW_ADRESS);

        accountActivity.buttonUpdateClicked();

        assertTrue(korisnik.getIdKorisnik() == 1);
        assertEquals(korisnik.getIme(), KORISNIK_NAME);
        assertEquals(korisnik.getPrezime(), KORISNIK_SURNAME);
        assertEquals(korisnik.getMail(), KORISNIK_MAIL);
        assertEquals(korisnik.getOib(), KORISNIK_OIB);
        assertEquals(korisnik.getAdresa(), KORISNIK_ADRESS);
        assertEquals(korisnik.getBrojMob(), KORISNIK_PHONE);
        assertEquals(korisnik.getLozinka(), KORISNIK_PASSWORD);

        verify(userApi, times(0)).update(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void dontUpdateKorisnik_onButtonUpdateClicked_withBadSurname() {
        accountActivity.editTextIme.setText(KORISNIK_NEW_NAME);
        accountActivity.editTextPrezime.setText(KORISNIK_BAD_VALUE);
        accountActivity.editTextEmail.setText(KORISNIK_NEW_MAIL);
        accountActivity.textViewOib.setText(KORISNIK_OIB);
        accountActivity.editTextBrojMobitela.setText(KORISNIK_NEW_PHONE);
        accountActivity.editTextAdresa.setText(KORISNIK_NEW_ADRESS);

        accountActivity.buttonUpdateClicked();

        assertTrue(korisnik.getIdKorisnik() == 1);
        assertEquals(korisnik.getIme(), KORISNIK_NAME);
        assertEquals(korisnik.getPrezime(), KORISNIK_SURNAME);
        assertEquals(korisnik.getMail(), KORISNIK_MAIL);
        assertEquals(korisnik.getOib(), KORISNIK_OIB);
        assertEquals(korisnik.getAdresa(), KORISNIK_ADRESS);
        assertEquals(korisnik.getBrojMob(), KORISNIK_PHONE);
        assertEquals(korisnik.getLozinka(), KORISNIK_PASSWORD);

        verify(userApi, times(0)).update(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void dontUpdateKorisnik_onButtonUpdateClicked_withBadEmail() {
        accountActivity.editTextIme.setText(KORISNIK_NEW_NAME);
        accountActivity.editTextPrezime.setText(KORISNIK_NEW_SURNAME);
        accountActivity.editTextEmail.setText(KORISNIK_BAD_VALUE);
        accountActivity.textViewOib.setText(KORISNIK_OIB);
        accountActivity.editTextBrojMobitela.setText(KORISNIK_NEW_PHONE);
        accountActivity.editTextAdresa.setText(KORISNIK_NEW_ADRESS);

        accountActivity.buttonUpdateClicked();

        assertTrue(korisnik.getIdKorisnik() == 1);
        assertEquals(korisnik.getIme(), KORISNIK_NAME);
        assertEquals(korisnik.getPrezime(), KORISNIK_SURNAME);
        assertEquals(korisnik.getMail(), KORISNIK_MAIL);
        assertEquals(korisnik.getOib(), KORISNIK_OIB);
        assertEquals(korisnik.getAdresa(), KORISNIK_ADRESS);
        assertEquals(korisnik.getBrojMob(), KORISNIK_PHONE);
        assertEquals(korisnik.getLozinka(), KORISNIK_PASSWORD);

        verify(userApi, times(0)).update(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void dontUpdateKorisnik_onButtonUpdateClicked_withBadPhone() {
        accountActivity.editTextIme.setText(KORISNIK_NEW_NAME);
        accountActivity.editTextPrezime.setText(KORISNIK_NEW_SURNAME);
        accountActivity.editTextEmail.setText(KORISNIK_NEW_MAIL);
        accountActivity.textViewOib.setText(KORISNIK_OIB);
        accountActivity.editTextBrojMobitela.setText(KORISNIK_BAD_VALUE);
        accountActivity.editTextAdresa.setText(KORISNIK_NEW_ADRESS);

        accountActivity.buttonUpdateClicked();

        assertTrue(korisnik.getIdKorisnik() == 1);
        assertEquals(korisnik.getIme(), KORISNIK_NAME);
        assertEquals(korisnik.getPrezime(), KORISNIK_SURNAME);
        assertEquals(korisnik.getMail(), KORISNIK_MAIL);
        assertEquals(korisnik.getOib(), KORISNIK_OIB);
        assertEquals(korisnik.getAdresa(), KORISNIK_ADRESS);
        assertEquals(korisnik.getBrojMob(), KORISNIK_PHONE);
        assertEquals(korisnik.getLozinka(), KORISNIK_PASSWORD);

        verify(userApi, times(0)).update(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void dontUpdateKorisnik_onButtonUpdateClicked_withBadAdress() {
        accountActivity.editTextIme.setText(KORISNIK_NEW_NAME);
        accountActivity.editTextPrezime.setText(KORISNIK_NEW_SURNAME);
        accountActivity.editTextEmail.setText(KORISNIK_NEW_MAIL);
        accountActivity.textViewOib.setText(KORISNIK_OIB);
        accountActivity.editTextBrojMobitela.setText(KORISNIK_NEW_PHONE);
        accountActivity.editTextAdresa.setText(KORISNIK_BAD_VALUE);

        accountActivity.buttonUpdateClicked();

        assertTrue(korisnik.getIdKorisnik() == 1);
        assertEquals(korisnik.getIme(), KORISNIK_NAME);
        assertEquals(korisnik.getPrezime(), KORISNIK_SURNAME);
        assertEquals(korisnik.getMail(), KORISNIK_MAIL);
        assertEquals(korisnik.getOib(), KORISNIK_OIB);
        assertEquals(korisnik.getAdresa(), KORISNIK_ADRESS);
        assertEquals(korisnik.getBrojMob(), KORISNIK_PHONE);
        assertEquals(korisnik.getLozinka(), KORISNIK_PASSWORD);

        verify(userApi, times(0)).update(any(Korisnik.class));
    }

}