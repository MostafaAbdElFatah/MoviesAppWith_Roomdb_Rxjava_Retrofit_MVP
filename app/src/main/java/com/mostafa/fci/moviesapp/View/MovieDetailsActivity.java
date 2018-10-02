package com.mostafa.fci.moviesapp.View;

import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mostafa.fci.moviesapp.Model.Database.RoomDB.RoomManager;
import com.mostafa.fci.moviesapp.Model.Movies.Movie;
import com.mostafa.fci.moviesapp.Model.Network.NetworkState;
import com.mostafa.fci.moviesapp.Model.Network.URLs;
import com.mostafa.fci.moviesapp.Presenter.MovieDetailsPresenter;
import com.mostafa.fci.moviesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends YouTubeBaseActivity {

    Movie movie = null;
    YouTubePlayerView youTube;
    MovieDetailsPresenter mPresenter;
    FloatingActionButton mFloatingABTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        mFloatingABTN = findViewById(R.id.fab);
        //TextView movieTextView = findViewById(R.id.movieName);
        TextView taglineTextView = findViewById(R.id.tagline);
        TextView yearTextView = findViewById(R.id.year);
        TextView durationTextView = findViewById(R.id.duration);
        TextView directorTextView = findViewById(R.id.director);
        TextView castTextView = findViewById(R.id.cast);
        TextView storyTextView = findViewById(R.id.story);
        RatingBar ratingTextView = findViewById(R.id.rating);

        mPresenter = new MovieDetailsPresenter(this);

        Intent intent = getIntent();
        movie = (Movie) intent.getSerializableExtra("movie");
        int index = intent.getIntExtra("index",0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setActionBar(toolbar);
        }
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle(movie.getMovie());
        // set values to views
        //movieTextView.setText(movie.getMovie());
        taglineTextView.setText(movie.getTagline());
        yearTextView.setText(String.valueOf(movie.getYear()));
        durationTextView.setText(movie.getDuration());
        directorTextView.setText(movie.getDirector());
        String casts = "";
        for (Movie.Cast cast : movie.getCast()) {
            casts += cast.getName();
        }
        castTextView.setText(casts);
        storyTextView.setText(movie.getStory());
        ratingTextView.setRating((float) movie.getRating());
        if (movie.isFavorite()) {
            mFloatingABTN.setImageResource(R.drawable.ic_favorite_red_24dp);
        } else {
            mFloatingABTN.setImageResource(R.drawable.ic_favorite_border_red_24dp);
        }

        final String videoCode = URLs.youtubeTraliers[index];
        String API_KEY = getResources().getString(R.string.youtube_api_key);
        youTube = findViewById(R.id.trailer);
        youTube.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoCode);
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(MovieDetailsActivity.this
                        , "Error whiling Loading Video"
                        , Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items, menu);
        menu.removeItem(R.id.refresh);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.favorite){
            startActivity(new Intent(MovieDetailsActivity.this,FavoriteActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    public void favoritebtnClicked(View view) {

        if (movie.isFavorite()) {
            mFloatingABTN.setImageResource(R.drawable.ic_favorite_border_red_24dp);
            movie.setFavorite(false);
            mPresenter.updateMovie(movie);
        } else {
            mFloatingABTN.setImageResource(R.drawable.ic_favorite_red_24dp);
            movie.setFavorite(true);
            mPresenter.updateMovie(movie);
        }
    }
}
