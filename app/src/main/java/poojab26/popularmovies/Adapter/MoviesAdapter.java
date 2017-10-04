package poojab26.popularmovies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static android.content.ContentValues.TAG;

/**
 * Created by pblead26 on 04-Oct-17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    private ItemClickListener mClickListener;

    private LayoutInflater mInflater;



    // data is passed into the constructor
   public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.movie_recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = movies.get(position).getTitle();
        holder.tvMovieName.setText(title);
        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.imgPoster);
    }


    // total number of cells
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMovieName;
        ImageView imgPoster;
        private final Context context;

        ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            tvMovieName = (TextView) itemView.findViewById(R.id.tv_moviename);
            imgPoster = (ImageView) itemView.findViewById(R.id.imgPoster);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Log.d(TAG, String.valueOf(getAdapterPosition()));
            final Intent intent;
            intent =  new Intent(context, DetailsActivity.class);
            intent.putExtra("pos", getAdapterPosition());
            context.startActivity(intent);
        }
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


    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
