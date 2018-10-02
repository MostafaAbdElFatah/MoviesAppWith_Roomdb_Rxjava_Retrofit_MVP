package com.mostafa.fci.moviesapp.Presenter;

import android.content.Context;

import com.mostafa.fci.moviesapp.Model.Database.RoomDB.RoomManager;
import com.mostafa.fci.moviesapp.Model.Movies;
import com.mostafa.fci.moviesapp.Model.Movies.Movie;


public class MovieDetailsPresenter implements IPresenter {

    Context context;
    RoomManager mRoomManager;

    public MovieDetailsPresenter(Context context){
        this.context = context;
        mRoomManager = new RoomManager(context);
    }

    @Override
    public void updateMovie(Movie movie) {
        mRoomManager.updateMovie(movie);
    }
}
