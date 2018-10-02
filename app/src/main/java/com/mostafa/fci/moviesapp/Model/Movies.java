package com.mostafa.fci.moviesapp.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.mostafa.fci.moviesapp.Model.Database.MovieConverter;

import java.io.Serializable;
import java.util.List;

public class Movies {

    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Entity
    public static class Movie implements Serializable {

        @PrimaryKey
        @NonNull
        @ColumnInfo
        private String movie;

        @ColumnInfo
        private int year;

        @ColumnInfo
        private double rating;

        @ColumnInfo
        private String duration;

        @ColumnInfo
        private String director;

        @ColumnInfo
        private String tagline;

        @ColumnInfo
        private String image;

        @ColumnInfo
        private String story;

        @ColumnInfo
        @TypeConverters(MovieConverter.class)
        private List<Cast> cast;

        private boolean favorite = false;

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public String getMovie() {
            return movie;
        }

        public void setMovie(String movie) {
            this.movie = movie;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getTagline() {
            return tagline;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }

        public List<Cast> getCast() {
            return cast;
        }

        public void setCast(List<Cast> cast) {
            this.cast = cast;
        }

        public static class Cast implements Serializable{

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
