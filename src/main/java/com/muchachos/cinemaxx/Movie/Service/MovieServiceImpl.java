package com.muchachos.cinemaxx.Movie.Service;

import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieServiceInterface {
    MovieRepo movieRepo;
    ModelMapper modelMapper; // for entity <--> DTO conversion

    private String errorMessage(int id){ return "Not found Movie with id = " + id;}


    @Autowired
    public MovieServiceImpl(MovieRepo movieRepo){
        this.movieRepo= movieRepo;
        this.modelMapper= new ModelMapper();
    }

    @Override
    public Movie getMovieByID(int id){
        return movieRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage(id)));
    }

    @Override
    public Movie getMovieByTitle(String title){
        return movieRepo.findMovieByTitle(title);
    }


}
