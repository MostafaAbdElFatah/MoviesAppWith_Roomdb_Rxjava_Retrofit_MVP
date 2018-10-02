package com.mostafa.fci.moviesapp.Presenter;

import android.content.Context;
import android.util.Log;

import com.mostafa.fci.moviesapp.Model.Database.RoomDB.RoomManager;
import com.mostafa.fci.moviesapp.Model.Movies;
import com.mostafa.fci.moviesapp.Model.Movies.Movie;
import com.mostafa.fci.moviesapp.Model.Network.ApiClient;
import com.mostafa.fci.moviesapp.Model.Network.ApiService;
import com.mostafa.fci.moviesapp.Model.Network.IMoviesList;
import com.mostafa.fci.moviesapp.Model.Network.NetworkState;
import com.mostafa.fci.moviesapp.View.IView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements IMoviesList {

    private IView view;
    private Context context;
    private RoomManager mRoomManager;
    private ApiService apiService;
    private CompositeDisposable mCompositeDisposable;

    public Presenter(Context context) {
        this.context = context;
        this.view = (IView) context;

        mRoomManager = new RoomManager(context);
        apiService = ApiClient.getClient(context.getApplicationContext()).create(ApiService.class);

        mCompositeDisposable = new CompositeDisposable();

        if (mRoomManager.isHasRows()) {
            //check database
            List<Movie> movies = mRoomManager.getMovies();
            this.moviesList(movies);
        } else if (NetworkState.isOnLine(context)) {
            //check internet
            fetchAllMovies();
        } else {
            //show Toast Error
            view.showInternetError();
        }
    }

    public void refreshData() {
        if (NetworkState.isOnLine(context)) {
            //check internet
            fetchAllMovies();
        } else {
            //show Toast Error
            view.showInternetError();
        }
    }


    private void fetchAllMovies() {
        mCompositeDisposable.add(
                apiService.getMoviesList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Movies>() {
                            @Override
                            public void onSuccess(Movies movies) {
                                mRoomManager.deleteAllMovies();
                                mRoomManager.saveMovies(movies.getMovies());
                                view.updateListView(movies.getMovies());
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Log.v("PRESENTER", "onError: " + e.getMessage());
                                view.showInternetError();
                            }
                        })
        );
    }

    @Override
    public void moviesList(List<Movie> movies) {
        view.updateListView(movies);
        mRoomManager.deleteAllMovies();
        mRoomManager.saveMovies(movies);
    }

    @Override
    public void onFailure() {
        view.showInternetError();
    }

    public void destroy() {
        mCompositeDisposable.clear();
    }
}
