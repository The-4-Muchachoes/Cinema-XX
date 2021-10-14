package com.muchachos.cinemaxx.Cinema.Repo;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepo extends CrudRepository<Cinema,Integer> {
}
