package hr.air1703.procare;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

import hr.air1703.procare.utils.ApplicationUtils;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ApplicationUtilsTest {

    @Test
    public void returnTrueOnValidMail_onIsMailValid() throws Exception {
        String mail = "mail@mail.com";

        assertTrue(ApplicationUtils.isMailValid(mail));
    }

    @Test
    public void returnFalseOnNotValidMail_onIsMailValid() throws Exception {
        String mail = "notValidMail";

        assertFalse(ApplicationUtils.isMailValid(mail));
    }

    @Test
    public void returnDateDifferenceInMinutes_onGetDateDiff() {
        Date dateStart = new Date();
        Date dateEnd = new Date();
        dateEnd.setTime(dateStart.getTime() + 900000);

        long dateDiff = ApplicationUtils.getDateDiff(dateStart, dateEnd, TimeUnit.MINUTES);

        assertEquals(dateDiff, 15L);
    }
}