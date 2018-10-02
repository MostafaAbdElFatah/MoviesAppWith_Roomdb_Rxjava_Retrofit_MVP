package com.mostafa.fci.moviesapp.Model.Database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.mostafa.fci.moviesapp.Model.Movies;
import com.mostafa.fci.moviesapp.Model.Movies.Movie;

public class FavoriteMovieConverter {
    @TypeConverter
    public Movies.Movie storedStringToOrder(String value) {
        Gson json = new Gson();
        Movie movie= json.fromJson(value,Movie.class);
        return movie;
    }

    @TypeConverter
    public String orderToString(Movie movie) {
        Gson json = new Gson();
        return json.toJson(movie);
    }
}
