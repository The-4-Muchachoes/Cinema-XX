package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Exceptions.EntityNotFoundException;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.DTO.CreateScreeningRequest;
import com.muchachos.cinemaxx.Screening.DTO.EditScreeningRequest;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningView;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Seat.Service.SeatService;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

    SeatService seatService;

    ModelMapper modelMapper;

    public ScreeningServiceImpl(ScreeningRepo screeningRepo, MovieRepo movieRepo,
                                TheaterRepo theaterRepo, SeatService seatService) {
        this.screeningRepo = screeningRepo;
        this.movieRepo = movieRepo;
        this.theaterRepo = theaterRepo;
        this.seatService = seatService;

        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ScreeningView getScreeningById(int id) {
        Screening screening = screeningRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(screening, ScreeningView.class);
    }

    @Override
    public List<ScreeningView> getTitleTimeAndRatingByCinemaAndDate(int cinemaId, LocalDate startDate, LocalDate endDate) {
        List<Theater> theaters = theaterRepo.findAllByCinema_Id(cinemaId);
        List<Screening> screenings = new ArrayList<>();
        List<ScreeningView> screeningViews = new ArrayList<>();

        // If endTime is null returns all screening for the date of startTime, else between given dates
        // endDate is inclusive
        LocalDateTime today = startDate.atStartOfDay();
        LocalDateTime tomorrow;
        if (endDate == null)
             tomorrow = today.plusHours(23).plusMinutes(59);
        else
            tomorrow = endDate.atTime(23,59);

        if (theaters.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // Add screenings per theater hall to list
        for (Theater theater : theaters) {
            screenings.addAll(screeningRepo.findAllByStartTimeBetweenAndTheater_IdOrderByStartTime(
                    today, tomorrow, theater.getId())
            );
        }

        // Create DTOs per screening and add them to list
        for (Screening screening : screenings) {
            screeningViews.add(modelMapper.map(screening, ScreeningView.class));
        }

        return screeningViews;
    }

    public ScreeningView addScreening(CreateScreeningRequest dto) {
        Movie movie = movieRepo.findById(dto.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("No movie exists by that ID"));

        Theater theater = theaterRepo.findById(dto.getTheaterId())
                .orElseThrow(() -> new EntityNotFoundException("No theater exists by that ID"));

        Screening screening = modelMapper.map(dto, Screening.class);
        screening.setMovie(movie);
        screening.setTheater(theater);
        screeningRepo.save(screening);

        // generate seats for the screening
        seatService.generateSeatsForScreening(screening);

        return modelMapper.map(screening, ScreeningView.class);
    }

    @Override
    public ResponseEntity<?> editScreeningStartTime(EditScreeningRequest dto) {
        Screening screening = screeningRepo.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("No screening exists by that ID"));
        screening.setStartTime(dto.getStartTime());
        screeningRepo.save(screening);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> deleteScreening(int id) {

        if (!screeningRepo.existsById(id)) throw new EntityNotFoundException("No screening exists by that ID");
        else screeningRepo.deleteById(id);

        if (!screeningRepo.existsById(id)) return ResponseEntity.noContent().build();
        else throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
