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
    TextView tvText;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvText = (TextView)findViewById(R.id.textDetails);

        Intent movieIntent = getIntent();
        int movieID = movieIntent.getIntExtra("movieID", 0);
        int pos = movieIntent.getIntExtra("pos", 0);

        tvText.setText(String.valueOf(movieID));

        loadMoviesListJSON(movieID, pos);
    }


    private void loadMoviesListJSON(final int movieID, final int pos) {
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        Call<PopularMoviesList> call = apiInterface.getPopularMovies("f4695866012a87780251b61af89bb5ac");
        call.enqueue(new Callback<PopularMoviesList>() {
            @Override
            public void onResponse(Call<PopularMoviesList> call, Response<PopularMoviesList> response) {

                List<Movie> popularMovyLists = response.body().getMovies();
                Log.d("TAG1", popularMovyLists.get(0).getOriginalLanguage());

                if (popularMovyLists.get(pos).getId()==movieID){
                    Log.d("TAG2", popularMovyLists.get(pos).getOriginalTitle());
                }

            }

            @Override
            public void onFailure(Call<PopularMoviesList> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
