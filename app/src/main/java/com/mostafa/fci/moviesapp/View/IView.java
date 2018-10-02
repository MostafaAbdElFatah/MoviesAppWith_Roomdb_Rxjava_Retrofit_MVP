package com.mostafa.fci.moviesapp.View;

import com.mostafa.fci.moviesapp.Model.Movies.Movie;

import java.util.List;

public interface IView {

   public void updateListView(List<Movie> movies);
   public void showInternetError();
}
