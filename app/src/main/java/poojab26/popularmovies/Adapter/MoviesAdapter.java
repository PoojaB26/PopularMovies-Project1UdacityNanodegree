package poojab26.popularmovies.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import poojab26.popularmovies.Activity.DetailsActivity;
import poojab26.popularmovies.Model.Movie;
import poojab26.popularmovies.R;

/**
 * Created by pblead26 on 04-Oct-17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final List<Movie> movies;
    private final OnItemClickListener listener;

    String BASE_PATH = "http://image.tmdb.org/t/p/w185/";



    // data is passed into the constructor
   public MoviesAdapter(List<Movie> movies, OnItemClickListener listener) {
       this.movies = movies;
       this.listener = listener;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position, listener);
      /*  String title = movies.get(position).getTitle();
        holder.tvMovieName.setText(title);
        String ImagePath = movies.get(position).getPosterPath();
        Picasso.with(context).load(BASE_PATH+ImagePath).into(holder.imgPoster);
   */ }



    // total number of cells
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieName;
        ImageView imgPoster;
       // private final Context context;

        ViewHolder(View itemView) {
            super(itemView);
        //    context = itemView.getContext();

            tvMovieName = (TextView) itemView.findViewById(R.id.tv_moviename);
            imgPoster = (ImageView) itemView.findViewById(R.id.imgPoster);

          //  itemView.setOnClickListener(this);
        }

        public void bind(final int position, final OnItemClickListener listener) {
            String title = movies.get(position).getTitle();
            tvMovieName.setText(title);
            String ImagePath = movies.get(position).getPosterPath();
            Picasso.with(itemView.getContext()).load(BASE_PATH+ImagePath).into(imgPoster);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(position);
                    Movie movie = movies.get(position);
                    Intent i = new Intent(itemView.getContext(), DetailsActivity.class);
                    i.putExtra("Movie", movie); // using the (String name, Parcelable value) overload!
                    itemView.getContext().startActivity(i);

                }
            });
        }



      /*  @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Log.d(TAG, String.valueOf(getAdapterPosition()));
            final Intent intent;
            intent =  new Intent(context, DetailsActivity.class);
            intent.putExtra("pos", getAdapterPosition());
            context.startActivity(intent);
        }*/
    }
/*
    // convenience method for getting data at click position
   public String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }*/




}
