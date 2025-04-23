package com.moviesdb.moviesapp.controllers;
import org.springframework.web.bind.annotation.*;

import com.moviesdb.moviesapp.models.Movie;
import com.moviesdb.moviesapp.services.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);

        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.status(404).body("Movie with ID " + id + " not found"); 
        }
    }

    @PostMapping("/store")
    public Optional<Movie> storeMovie(@RequestBody Movie movie) {
        return movieService.storeMovie(movie);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Optional<Movie> updated = movieService.updateMovie(id, movie);

        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(404).body("Movie with ID " + id + " not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        Optional<?> result = movieService.deleteMovie(id);

        if (result.isPresent()) {
            return ResponseEntity.status(201).body("Movie with ID " + id + " has been deleted");
        } else {
            return ResponseEntity.status(404).body("Movie with ID " + id + " not found");

        }
    }

    
}

