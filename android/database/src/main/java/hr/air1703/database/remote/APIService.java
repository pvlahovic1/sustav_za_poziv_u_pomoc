package hr.air1703.database.remote;
import hr.air1703.database.model.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * This interface contains methods used to execute HTTP requests
 * such as POST, PUT, DELETE
 */

public interface APIService {

    // Login
    @POST("/rest-api/korisnik/login")
    //@FormUrlEncoded
    Call<Post> sendLogin(@Body Post post);


    // Registration
    // TO DO

}
