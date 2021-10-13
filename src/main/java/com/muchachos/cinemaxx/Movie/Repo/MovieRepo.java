package com.muchachos.cinemaxx.Movie.Repo;

import com.muchachos.cinemaxx.Movie.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepo extends JpaRepository<Movie,Integer> {
    Movie findMovieById(int id);
}
