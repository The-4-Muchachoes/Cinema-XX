package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTOWithTitleAndRating;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    ScreeningRepo screeningRepo;

    MovieRepo movieRepo;

    TheaterRepo theaterRepo;

    ModelMapper modelMapper = new ModelMapper();

    public ScreeningServiceImpl(ScreeningRepo screeningRepo, MovieRepo movieRepo, TheaterRepo theaterRepo) {
        this.screeningRepo = screeningRepo;
        this.movieRepo = movieRepo;
        this.theaterRepo = theaterRepo;
    }

    @Override
    public List<ScreeningDTOWithTitleAndRating> getTitleTimeAndRatingByCinemaAndDate(int cinemaId, LocalDate date) {
        List<Theater> theaters = theaterRepo.findAllByCinema_Id(cinemaId);
        List<Screening> screenings = new ArrayList<>();
        List<ScreeningDTOWithTitleAndRating> screeningDTOS = new ArrayList<>();

        // Database stores a timestamp so the below datetime objects are necessary to query a single date
        LocalDateTime today = startDate.atStartOfDay();
        LocalDateTime tomorrow;
        if (endDate == null)
             tomorrow = today.plusHours(23).plusMinutes(59);
        else
            tomorrow = endDate.atTime(23,59);

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
            screeningDTOS.add(new ScreeningDTOWithTitleAndRating(null, screening.getStartTime(), movie.getTitle(), movie.getRating()));
        }

        return screeningDTOS;
    }

    public ScreeningDTOWithTitleAndRating addScreening(int movie_id, int theater_id, LocalDateTime startTime ) {
        Movie m = movieRepo.findById(movie_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Theater t = theaterRepo.findById(theater_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Screening s = new Screening(null, startTime, m,t);
        Screening  saved = screeningRepo.save(s);
        return  new ScreeningDTOWithTitleAndRating(saved.getId(),saved.getStartTime(), saved.getMovie().getTitle(), saved.getMovie().getRating());

    }

    public ScreeningDTO editScreening(ScreeningDTO dto) {
        if (dto.getId() == null || dto.getStartTime() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Screening screening = screeningRepo.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        screening.setStartTime(dto.getStartTime());

        return modelMapper.map(screeningRepo.save(screening), ScreeningDTO.class);
    }

    public ResponseEntity<?> deleteScreening(int id) {

        if (!screeningRepo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        screeningRepo.deleteById(id);
        if (!screeningRepo.existsById(id)) return ResponseEntity.noContent().build();
        else throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
