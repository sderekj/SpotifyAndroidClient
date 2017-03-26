package nyc.c4q.dereksantos.spotifyfellowship;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SpotifyApi {

    @GET("people")
    Call<List<Person>> getPeople();

    @GET("people/{id}")
    Call<Person> getPerson(@Path("id") String id);

    @FormUrlEncoded
    @POST("people")
    Call<Person> createPerson(@Field("name") String name, @Field("favoriteCity") String favoriteCity);

    @FormUrlEncoded
    @PUT("people")
    Call<Person> putPerson(@FieldMap Map<String, String> fields);

}
