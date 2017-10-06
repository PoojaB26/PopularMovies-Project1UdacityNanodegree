package poojab26.popularmovies;

import poojab26.popularmovies.Model.MoviesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pblead26 on 04-Oct-17.
 */

public interface ApiInterface {
/*

    @Headers("user_key: c031832bac8def9a5efadcb27718dfeb")
    @GET("geocode")
    Call<RestaurantList> getRestaurantList(@Query("lat")String lat, @Query("lon")String lon);
*/


    @GET("movie/popular")
    Call<MoviesList> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesList> getTopRatedMovies(@Query("api_key") String apiKey);

}
