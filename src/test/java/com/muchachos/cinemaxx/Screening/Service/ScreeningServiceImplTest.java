package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningView;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Seat.Repo.SeatRepo;
import com.muchachos.cinemaxx.Seat.Service.SeatService;
import com.muchachos.cinemaxx.Seat.Service.SeatServiceImpl;
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
    SeatRepo seatRepo;

    SeatService seatService;

    ScreeningServiceImpl screeningService;

    @BeforeEach
    public void setupData() {
        seatService = new SeatServiceImpl(seatRepo, screeningRepo, theaterRepo);
        screeningService = new ScreeningServiceImpl(screeningRepo, movieRepo, theaterRepo, seatService);

//        TestDataMaker.makeMovies(movieRepo);
//        TestDataMaker.makeCinemas(cinemaRepo);
//        TestDataMaker.makeTheaters(theaterRepo, cinemaRepo);
//        TestDataMaker.makeScreenings(screeningService, theaterRepo, movieRepo);
        TestDataMaker.makeTestData(cinemaRepo, theaterRepo, movieRepo, screeningService);
    }

    @Test
    void getTitleTimeAndRatingByCinemaAndDate() {
        List<ScreeningView> dtos = screeningService.getTitleTimeAndRatingByCinemaAndDate(1, LocalDate.now(), null);
        assertEquals(4, dtos.size());
        assertEquals("Ironman", dtos.get(0).getMovie().getTitle());
    }
}