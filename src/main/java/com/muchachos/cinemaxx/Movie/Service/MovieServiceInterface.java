package com.muchachos.cinemaxx.Movie.Service;

import com.muchachos.cinemaxx.Movie.Entity.Movie;

public interface MovieServiceInterface {
    Movie getMovieByID(int id);
    Movie getMovieByTitle(String title);
}
