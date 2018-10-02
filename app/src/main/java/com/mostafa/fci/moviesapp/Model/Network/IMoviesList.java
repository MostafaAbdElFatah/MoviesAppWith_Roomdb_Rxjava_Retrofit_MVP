package com.mostafa.fci.moviesapp.Model.Network;

import com.mostafa.fci.moviesapp.Model.Movies.Movie;

import java.util.List;

public interface IMoviesList {
    public void moviesList(List<Movie> movies);
    public void onFailure();
}
