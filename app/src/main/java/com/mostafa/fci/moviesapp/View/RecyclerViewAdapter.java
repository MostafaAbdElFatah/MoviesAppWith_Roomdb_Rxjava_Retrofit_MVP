package com.mostafa.fci.moviesapp.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mostafa.fci.moviesapp.Model.Movies.Movie;
import com.mostafa.fci.moviesapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    List<Movie> mMovies;
    OnClickedListener listener;

    public RecyclerViewAdapter(List<Movie> mMovies){
        this.mMovies = mMovies;
    }

    public void setOnClickedListener(OnClickedListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item
                ,parent,false);
        return  new Holder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Movie movie = mMovies.get(position);

        holder.movieName.setText(movie.getMovie());
        holder.movieYear.setText(String.valueOf(movie.getYear()));
        holder.movieDuration.setText(movie.getDuration());
        holder.movieDirector.setText(movie.getDirector());
        holder.movieTagline.setText(movie.getTagline());

        Picasso.get()
                .load(movie.getImage())
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView movieImage;
        TextView movieName, movieDuration, movieTagline, movieYear, movieDirector;

        public Holder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.movieImage    = itemView.findViewById(R.id.movie_image);
            this.movieName     = itemView.findViewById(R.id.movie);
            this.movieDuration = itemView.findViewById(R.id.duration);
            this.movieTagline  = itemView.findViewById(R.id.tagline);
            this.movieYear     = itemView.findViewById(R.id.year);
            this.movieDirector = itemView.findViewById(R.id.director);
        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onClicked(getLayoutPosition());
        }
    }

    public interface OnClickedListener{
        public void onClicked(int position);
    }

}
