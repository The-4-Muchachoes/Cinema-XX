package com.muchachos.cinemaxx.Movie.Controller;

import com.muchachos.cinemaxx.Movie.DTO.MovieDTO;
import com.muchachos.cinemaxx.Movie.Service.MovieServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    MovieServiceImpl movieService;

    @GetMapping(path = "/api/public/movies/{id}")
    MovieDTO getMovie(@PathVariable int id) {
        return modelMapper.map(movieService.getMovieByID(id), MovieDTO.class);
    }

    @GetMapping(path = "/api/public/movies")
    MovieDTO getMovieByTitle(@RequestParam String title){
        return modelMapper.map(movieService.getMovieByTitle(title), MovieDTO.class);
    }


}
