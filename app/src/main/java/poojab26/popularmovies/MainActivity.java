package poojab26.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import poojab26.popularmovies.Adapter.MoviesAdapter;
import poojab26.popularmovies.Model.Movie;
import poojab26.popularmovies.Model.PopularMoviesList;
import poojab26.popularmovies.Utilities.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MoviesAdapter adapter;
    final String API_KEY = "f4695866012a87780251b61af89bb5ac";
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};

         recyclerView = (RecyclerView) findViewById(R.id.rvMovies);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        loadPopularMoviesListJSON();
    }

    private void loadPopularMoviesListJSON() {
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        Call<PopularMoviesList> call = apiInterface.getPopularMovies(API_KEY);
        call.enqueue(new Callback<PopularMoviesList>() {
            @Override
            public void onResponse(Call<PopularMoviesList> call, Response<PopularMoviesList> response) {

                List<Movie> movies = response.body().getMovies();
                Log.d("TAG", movies.get(0).getOriginalLanguage());

                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.movie_recycler_view_item, MainActivity.this));

            }

            @Override
            public void onFailure(Call<PopularMoviesList> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }



}
