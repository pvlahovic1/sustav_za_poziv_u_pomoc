package hr.air1703.procare.utils;

import java.util.regex.Pattern;

/**
 * Created by pvlahovic on 31.10.2017..
 */

public class ApplicationUtils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

}
