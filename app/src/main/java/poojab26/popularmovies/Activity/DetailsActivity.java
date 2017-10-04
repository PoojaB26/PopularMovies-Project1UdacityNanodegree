package poojab26.popularmovies.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import poojab26.popularmovies.Model.PopularMoviesList;
import poojab26.popularmovies.Model.Movie;
import poojab26.popularmovies.R;
import poojab26.popularmovies.ApiInterface;
import poojab26.popularmovies.Utilities.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    TextView tvMovieTitle, tvSynopsis, tvRating, tvRelease;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvMovieTitle = (TextView)findViewById(R.id.tvOrigTitle);
        tvSynopsis = (TextView)findViewById(R.id.tvSynopsis);
        tvRating = (TextView)findViewById(R.id.tvRating);
        tvRelease = (TextView)findViewById(R.id.tvRelease);

        Intent movieIntent = getIntent();
        int pos = movieIntent.getIntExtra("pos", 0);
        loadMoviesListJSON(pos);
    }


    private void loadMoviesListJSON(final int pos) {
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        Call<PopularMoviesList> call = apiInterface.getPopularMovies("f4695866012a87780251b61af89bb5ac");
        call.enqueue(new Callback<PopularMoviesList>() {
            @Override
            public void onResponse(Call<PopularMoviesList> call, Response<PopularMoviesList> response) {

                List<Movie> movies = response.body().getMovies();

                tvMovieTitle.setText(movies.get(pos).getOriginalTitle());
                tvSynopsis.setText(movies.get(pos).getOverview());
                tvRating.setText(String.valueOf(movies.get(pos).getVoteAverage()));
                tvRelease.setText(movies.get(pos).getReleaseDate());


            }

            @Override
            public void onFailure(Call<PopularMoviesList> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
