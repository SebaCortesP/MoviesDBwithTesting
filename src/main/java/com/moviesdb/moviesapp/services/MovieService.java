package com.moviesdb.moviesapp.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviesdb.moviesapp.models.Movie;
import com.moviesdb.moviesapp.repositories.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> updateMovie(Long id, Movie movie){
        Boolean existMovie = movieRepository.existsById(id);
        if(existMovie){
            movie.setId(id);
            return Optional.of(movieRepository.save(movie));
            
        }else{
            return Optional.empty();
        }
    }
    
    public Optional<?> deleteMovie(Long id){
        Boolean existMovie = movieRepository.existsById(id);
        if(existMovie){
            movieRepository.deleteById(id);
            return Optional.of("Deleted");
        }else{
            return Optional.empty();
        }
    }

    public Optional<Movie> storeMovie(Movie movie){
        return Optional.of(movieRepository.save(movie));
    }
}
