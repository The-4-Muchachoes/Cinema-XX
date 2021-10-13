package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import com.muchachos.cinemaxx.testUtils.TestDataMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScreeningServiceImplTest {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    CinemaRepo cinemaRepo;

    @Autowired
    TheaterRepo theaterRepo;

    @Autowired
    ScreeningRepo screeningRepo;

    @Autowired
    ScreeningService screeningService;

    @BeforeEach
    public void setupData() {
        TestDataMaker.makeMovies(movieRepo);
        TestDataMaker.makeCinemas(cinemaRepo);
        TestDataMaker.makeTheaters(theaterRepo, cinemaRepo);
        TestDataMaker.makeScreenings(screeningRepo, theaterRepo, movieRepo);
    }

    @Test
    void getTitleTimeAndRatingByCinemaAndDate() {
        List<ScreeningDTO> dtos = screeningService.getTitleTimeAndRatingByCinemaAndDate(0, LocalDate.now());
        assertEquals(4, dtos.size());
        assertEquals("Batman", dtos.get(0).getTitle());
    }
}