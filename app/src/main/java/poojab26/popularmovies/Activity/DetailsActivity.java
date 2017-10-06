package poojab26.popularmovies.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import poojab26.popularmovies.ApiInterface;
import poojab26.popularmovies.Model.Movie;
import poojab26.popularmovies.Model.MoviesList;
import poojab26.popularmovies.R;
import poojab26.popularmovies.Utilities.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    TextView tvMovieTitle, tvSynopsis, tvRating, tvRelease;
    ImageView tvMovieBackground;
    ApiInterface apiInterface;
    String BASE_PATH = "http://image.tmdb.org/t/p/w342/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvMovieBackground = (ImageView)findViewById(R.id.imgBackground);
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

        Call<MoviesList> call = apiInterface.getPopularMovies(getString(R.string.API_KEY));
        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {

                List<Movie> movies = response.body().getMovies();
                String path = movies.get(pos).getBackdropPath();
                Picasso.with(getApplicationContext()).load(BASE_PATH+path).into(tvMovieBackground);

                tvMovieTitle.setText(movies.get(pos).getOriginalTitle());
                tvSynopsis.setText(movies.get(pos).getOverview());
                tvRating.setVisibility(View.VISIBLE);
                tvRating.setText(String.valueOf(movies.get(pos).getVoteAverage()));
                tvRelease.setText(getString(R.string.release_date) +movies.get(pos).getReleaseDate());


            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Log.d("Error", t.getMessage());
                setContentView(R.layout.layout_no_network);

            }
        });
    }
}
