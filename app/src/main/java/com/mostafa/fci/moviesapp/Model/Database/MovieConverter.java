package com.mostafa.fci.moviesapp.Model.Database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.mostafa.fci.moviesapp.Model.Movies;
import com.mostafa.fci.moviesapp.Model.Movies.Movie;

import java.util.ArrayList;
import java.util.List;


public class MovieConverter {
    @TypeConverter
    public List<Movies.Movie.Cast> storedStringToOrder(String value) {
        Gson json = new Gson();
        Movie.Cast[] castArray = json.fromJson(value,Movie.Cast[].class);
        List<Movie.Cast> castList = new ArrayList<>();
        for (Movie.Cast cast:castArray) {
            castList.add(cast);
        }
        return castList;
    }

    @TypeConverter
    public String orderToString(List<Movie.Cast> casts) {
        Gson json = new Gson();
        return json.toJson(casts);
    }
}