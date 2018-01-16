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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> rule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    @Mock
    private UserApi userApi;

    RegisterActivity registerActivity;

    @Before
    public void setup() {
        registerActivity = spy(rule.getActivity());

        registerActivity.editTextIme.setText("imeTest");
        registerActivity.editTextPrezime.setText("prezimeTest");
        registerActivity.editTextEmail.setText("mail@mail.com");
        registerActivity.editTextOib.setText("12345678903");
        registerActivity.editTextAdresa.setText("Adresa Test");
        registerActivity.editTextBrojMobitela.setText("0987654321");
        registerActivity.editTextLozinka.setText("lozinka");

        userApi = mock(UserApi.class);
   }

    @Test
    @UiThreadTest
    public void registerUser_onButtonRegistracijaClicked() throws Exception {
        registerActivity.userApi = userApi;
        registerActivity.buttonRegistracijaClicked();

        verify(userApi, times(1)).register(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void DontRegisterUser_onButtonRegistracijaClicked_whenBadIme() throws Exception {
        registerActivity.editTextIme.setText("");
        registerActivity.userApi = userApi;
        registerActivity.buttonRegistracijaClicked();

        verify(userApi, times(0)).register(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void DontRegisterUser_onButtonRegistracijaClicked_whenBadPrezime() throws Exception {
        registerActivity.editTextPrezime.setText("");
        registerActivity.userApi = userApi;
        registerActivity.buttonRegistracijaClicked();

        verify(userApi, times(0)).register(any(Korisnik.class));
    }


    @Test
    @UiThreadTest
    public void DontRegisterUser_onButtonRegistracijaClicked_whenBadEmail() throws Exception {
        registerActivity.editTextEmail.setText("");
        registerActivity.userApi = userApi;
        registerActivity.buttonRegistracijaClicked();

        verify(userApi, times(0)).register(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void DontRegisterUser_onButtonRegistracijaClicked_whenBadOIB() throws Exception {
        registerActivity.editTextOib.setText("");
        registerActivity.userApi = userApi;
        registerActivity.buttonRegistracijaClicked();

        verify(userApi, times(0)).register(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void DontRegisterUser_onButtonRegistracijaClicked_whenBadAdresa() throws Exception {
        registerActivity.editTextAdresa.setText("");
        registerActivity.userApi = userApi;
        registerActivity.buttonRegistracijaClicked();

        verify(userApi, times(0)).register(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void DontRegisterUser_onButtonRegistracijaClicked_whenBadBrojMobitela() throws Exception {
        registerActivity.editTextBrojMobitela.setText("");
        registerActivity.userApi = userApi;
        registerActivity.buttonRegistracijaClicked();

        verify(userApi, times(0)).register(any(Korisnik.class));
    }

    @Test
    @UiThreadTest
    public void DontRegisterUser_onButtonRegistracijaClicked_whenBadLozinka() throws Exception {
        registerActivity.editTextLozinka.setText("");
        registerActivity.userApi = userApi;
        registerActivity.buttonRegistracijaClicked();

        verify(userApi, times(0)).register(any(Korisnik.class));
    }

}
