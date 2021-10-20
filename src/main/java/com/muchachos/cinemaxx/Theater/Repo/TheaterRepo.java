package com.muchachos.cinemaxx.Theater.Repo;

import com.muchachos.cinemaxx.Theater.Entity.Theater;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

@Repository
public interface TheaterRepo extends CrudRepository<Theater,Integer> {

    List<Theater> findAllByCinema_Id(int id);
}
