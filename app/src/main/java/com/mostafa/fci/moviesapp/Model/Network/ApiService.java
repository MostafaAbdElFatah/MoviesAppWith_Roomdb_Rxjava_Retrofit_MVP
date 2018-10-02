package com.mostafa.fci.moviesapp.Model.Network;

import com.mostafa.fci.moviesapp.Model.Movies;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    /// GET Method Request without Parameters
    @GET("moviesData.txt")
    Single<Movies> getMoviesList();

}
