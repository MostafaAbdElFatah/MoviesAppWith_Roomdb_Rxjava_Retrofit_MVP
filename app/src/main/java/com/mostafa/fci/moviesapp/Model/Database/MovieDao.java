package com.mostafa.fci.moviesapp.Model.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.mostafa.fci.moviesapp.Model.Movies.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void saveMovie(Movie movie);

    @Insert
    void saveMovies(List<Movie> movieList);

    @Query("SELECT * FROM Movie WHERE movie = :id")
    Movie getMovie(String id);

    @Query ("SELECT * FROM Movie")
    List<Movie> getMovies();

    @Update
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("DELETE FROM Movie")
    void deleteAllMovies();

}
