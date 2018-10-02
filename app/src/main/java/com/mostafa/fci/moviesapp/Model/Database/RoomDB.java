package com.mostafa.fci.moviesapp.Model.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.mostafa.fci.moviesapp.Model.Movies.Movie;
import java.util.List;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends android.arch.persistence.room.RoomDatabase {

    abstract MovieDao movieDao();
    private static RoomDB mRoomManager;

    private static RoomDB getDatabase(final Context context) {
        if (mRoomManager == null) {
            synchronized (RoomDB.class) {
                if (mRoomManager == null) {
                    mRoomManager = Room.databaseBuilder(context,
                            RoomDB.class, "flower_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return mRoomManager;
    }
    ///

  public static class RoomManager {
        private Context context;

        public RoomManager(Context context){
            this.context = context;
            mRoomManager = getDatabase(context);
        }

         public void saveMovie(Movie movie) {
             mRoomManager.movieDao().saveMovie(movie);
         }

         public void saveMovies(List<Movie> movieList) {
             mRoomManager.movieDao().saveMovies(movieList);
         }

         public Movie getMovie(String id) {
             return mRoomManager.movieDao().getMovie(id);
         }

         public List<Movie> getMovies() {
             return mRoomManager.movieDao().getMovies();
         }

         public boolean isHasRows() {
             List<Movie> movies = mRoomManager.movieDao().getMovies();
             if (movies == null)
                 return false;
             else if (movies.size() <= 0)
                 return false;
             else
                 return true;
         }

         public void updateMovie(Movie movie) {
             mRoomManager.movieDao().updateMovie(movie);
         }

         public void deleteMovie(Movie movie) {
             mRoomManager.movieDao().deleteMovie(movie);
         }

         public void deleteAllMovies() {
             mRoomManager.movieDao().deleteAllMovies();
         }
    }
}