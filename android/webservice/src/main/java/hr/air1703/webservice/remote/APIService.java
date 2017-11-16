package hr.air1703.webservice.remote;

import java.util.List;

import hr.air1703.database.model.Korisnik;
import hr.air1703.database.model.Razlog;
import hr.air1703.webservice.remote.wrapper.OrganizacijaWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * This interface contains methods used to execute HTTP requests
 * such as POST, PUT, DELETE
 */

public interface APIService {

    @POST("/rest-api/korisnik/login")
    //@FormUrlEncoded
    Call<Korisnik> sendLogin(@Body Korisnik korisnik);

    @POST("/rest-api/korisnik")
    Call<Korisnik> sendRegister(@Body Korisnik korisnik);

    @POST("/rest-api/korisnik/update")
    Call<Korisnik> sendUpdate(@Body Korisnik korisnik);

    @GET("/rest-api/ogranizacija")
    Call<List<OrganizacijaWrapper>> getOrganizacije();

    @GET("/rest-api/poziv/razlozi")
    Call<List<Razlog>> getRazloziPozivaUPomoc();

}
