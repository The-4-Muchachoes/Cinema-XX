package com.muchachos.cinemaxx.Movie.Repo;

import com.muchachos.cinemaxx.Movie.Entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends CrudRepository<Movie,Integer> {
}
