package hr.air1703.database.remote;

/**
 * This class has the base URL as a static variable and also
 * provides the APIService interface with a getAPIService()
 * static method to the rest of the application.
 */

public class ApiUtils {

    private ApiUtils() {}

    private static final String BASE_URL = "http://185.28.22.163:9889/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
