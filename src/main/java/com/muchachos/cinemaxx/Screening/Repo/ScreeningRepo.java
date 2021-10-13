package com.muchachos.cinemaxx.Screening.Repo;

import com.muchachos.cinemaxx.Screening.Entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepo extends JpaRepository<Screening,Integer> {

    List<Screening> findAllByStartTimeBetweenAAndTheater_Id(LocalDateTime date, LocalDateTime tomorrow, int theaterId);
}
