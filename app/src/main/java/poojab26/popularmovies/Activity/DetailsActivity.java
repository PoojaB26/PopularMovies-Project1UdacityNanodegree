package poojab26.popularmovies.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import poojab26.popularmovies.Model.PopularMoviesList;
import poojab26.popularmovies.Model.PopularMovie;
import poojab26.popularmovies.R;
import poojab26.popularmovies.RequestInterface;
import poojab26.popularmovies.Utilities.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    TextView tvText;
    RequestInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvText = (TextView)findViewById(R.id.textDetails);

        Intent movieIntent = getIntent();
        String movieText = movieIntent.getStringExtra("message");


        loadMoviesListJSON();
    }


    private void loadMoviesListJSON() {
        apiInterface = APIClient.getClient().create(RequestInterface.class);

        Call<PopularMoviesList> call = apiInterface.getPopularMovies("f4695866012a87780251b61af89bb5ac");
        call.enqueue(new Callback<PopularMoviesList>() {
            @Override
            public void onResponse(Call<PopularMoviesList> call, Response<PopularMoviesList> response) {

                List<PopularMovie> popularMovyLists = response.body().getPopularMovies();
                Log.d("TAG", popularMovyLists.get(0).getOriginalLanguage());
               /* restaurantList = response.body();

                Log.d("success", "done");

                Log.d("popul",restaurantList.getNearbyRestaurants().get(0).getRestaurant().getId());
                restaurantIDList = new ArrayList<String>();
                for(int i=0; i<5; i++) {
                    restaurantIDList.add(restaurantList.getNearbyRestaurants().get(i).getRestaurant().getId());

                    R_Details restModel = new R_Details(restaurantList.getNearbyRestaurants().get(i).getRestaurant().getName(),
                            restaurantList.getNearbyRestaurants().get(i).getRestaurant().getFeaturedImage(),
                            restaurantList.getNearbyRestaurants().get(i).getRestaurant().getCuisines());
                    R_List.add(restModel);

                    mAdapter.notifyDataSetChanged();
                    Log.d("done", "done");
                }

                System.out.println(Arrays.toString(restaurantIDList.toArray()));
                // prepareData();*/

            }

            @Override
            public void onFailure(Call<PopularMoviesList> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
