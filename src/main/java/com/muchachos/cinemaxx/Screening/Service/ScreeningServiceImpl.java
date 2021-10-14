package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    ScreeningRepo screeningRepo;

    MovieRepo movieRepo;

    TheaterRepo theaterRepo;

    public ScreeningServiceImpl(ScreeningRepo screeningRepo, MovieRepo movieRepo, TheaterRepo theaterRepo) {
        this.screeningRepo = screeningRepo;
        this.movieRepo = movieRepo;
        this.theaterRepo = theaterRepo;
    }

    @Override
    public List<ScreeningDTO> getTitleTimeAndRatingByCinemaAndDate(int cinemaId, LocalDate date) {
        List<Theater> theaters = theaterRepo.findAllByCinema_Id(cinemaId);
        List<Screening> screenings = new ArrayList<>();
        List<ScreeningDTO> screeningDTOS = new ArrayList<>();

        // Database stores a timestamp so the below datetime objects are necessary to query a single date
        LocalDateTime today = date.atStartOfDay();
        LocalDateTime tomorrow = today.plusHours(23).plusMinutes(59);

        if (theaters.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // Add screenings per theater hall to list
        for (Theater theater : theaters) {
            screenings.addAll(screeningRepo.findAllByStartTimeBetweenAndTheater_Id(today, tomorrow, theater.getId()));
        }

        // Create DTOs per screening and add them to list
        for (Screening screening : screenings) {
            Movie movie = movieRepo.findById(
                    screening
                            .getMovie()
                            .getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            screeningDTOS.add(new ScreeningDTO(null, screening.getStartTime(), movie.getTitle(), movie.getRating()));
        }

        return screeningDTOS;
    }
    public ScreeningDTO addScreening(int movie_id, int theater_id, LocalDateTime startTime ) {
        Movie m = movieRepo.findById(movie_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Theater t = theaterRepo.findById(theater_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));



        Screening s = new Screening(null, startTime, m,t);
        Screening  saved = screeningRepo.save(s);
        return  new ScreeningDTO(saved.getId(),saved.getStartTime(), saved.getMovie().getTitle(), saved.getMovie().getRating());




    }
}
