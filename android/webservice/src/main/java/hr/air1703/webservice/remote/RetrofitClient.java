package hr.air1703.webservice.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class will create a singleton of Retrofit in the method getClient(String baseUrl) and return it to the caller.
 */

public class RetrofitClient {

    private static int READ_TIMEOUT = 60;
    private static int CONNECT_TIMEOUT = 5;
    private static Retrofit retrofit = null;

    private final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
