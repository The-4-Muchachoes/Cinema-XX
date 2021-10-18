package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Movie.DTO.MovieDTO;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Theater.DTO.TheaterDTO;
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
    public List<ScreeningDTO> getTitleTimeAndRatingByCinemaAndDate(int cinemaId, LocalDate startDate, LocalDate endDate) {
        List<Theater> theaters = theaterRepo.findAllByCinema_Id(cinemaId);
        List<Screening> screenings = new ArrayList<>();
        List<ScreeningDTO> screeningDTOS = new ArrayList<>();

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
            screeningDTOS.add(modelMapper.map(screening, ScreeningDTO.class));
        }

        return screeningDTOS;
    }

    public ScreeningDTO addScreening(ScreeningDTO dto) {
        Movie movie = movieRepo.findById(dto.getMovieId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

        Theater theater = theaterRepo.findById(dto.getTheaterId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        TheaterDTO theaterDTO = modelMapper.map(theater, TheaterDTO.class);

        dto.setMovie(movieDTO);
        dto.setTheater(theaterDTO);

        Screening screening = screeningRepo.save(modelMapper.map(dto, Screening.class));

        // generate seats for the screening
//        List<Seat> seats = new ArrayList<>();
//        for (int x : theater.getRow()) {
//            for (int y : theater.getSeat()) {
//                seats.add(new Seat(null, x, y, false, screening));
//            }
//        }
//        seatRepo.saveAll(seats);

        return modelMapper.map(screening, ScreeningDTO.class);
    }

    @Override
    public ScreeningDTO editScreeningStartTime(ScreeningDTO dto) {
        if (dto.getId() == null || dto.getStartTime() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Screening screening = screeningRepo.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        screening.setStartTime(dto.getStartTime());

        return modelMapper.map(screeningRepo.save(screening), ScreeningDTO.class);
    }

    @Override
    public ResponseEntity<?> deleteScreening(int id) {

        if (!screeningRepo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        screeningRepo.deleteById(id);
        if (!screeningRepo.existsById(id)) return ResponseEntity.noContent().build();
        else throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
