package com.muchachos.cinemaxx.Movie.Repo;

import com.muchachos.cinemaxx.Movie.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {
    Movie findMovieById(int id);
}
