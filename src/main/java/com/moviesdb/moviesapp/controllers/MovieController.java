package com.moviesdb.moviesapp.controllers;

import org.springframework.web.bind.annotation.*;

import com.moviesdb.moviesapp.exceptions.ErrorResponse;
import com.moviesdb.moviesapp.models.Movie;
import com.moviesdb.moviesapp.services.MovieService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Movie>>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();

        List<EntityModel<Movie>> movieResources = movies.stream()
                .map(movie -> EntityModel.of(movie,
                        linkTo(methodOn(MovieController.class).getMovieById(movie.getId())).withSelfRel(),
                        linkTo(methodOn(MovieController.class).getAllMovies()).withRel("all-movies")))
                .toList();

        CollectionModel<EntityModel<Movie>> collection = CollectionModel.of(movieResources,
                linkTo(methodOn(MovieController.class).getAllMovies()).withSelfRel());

        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable("id") Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);

        if (movie.isPresent()) {

            Movie mv = movie.get();
            EntityModel<Movie> movieModel = EntityModel.of(mv,
                    linkTo(methodOn(MovieController.class).getMovieById(mv.getId())).withSelfRel(),
                    linkTo(methodOn(MovieController.class).getAllMovies()).withRel("all-movies"));
            // return ResponseEntity.ok(movie.get());
            return ResponseEntity.ok(movieModel);

        } else {
            return ResponseEntity.status(404).body("Movie with ID " + id + " not found");
        }
    }

    @PostMapping("/store")
    public ResponseEntity<EntityModel<Movie>> storeMovie(@Valid @RequestBody Movie movie) {
        Movie savedMovie = movieService.storeMovie(movie)
                .orElseThrow(() -> new RuntimeException("Could not store movie"));

        EntityModel<Movie> movieResource = EntityModel.of(savedMovie,
                linkTo(methodOn(MovieController.class).getMovieById(savedMovie.getId())).withSelfRel(),
                linkTo(methodOn(MovieController.class).getAllMovies()).withRel("all-movies"));

        return ResponseEntity
                .created(linkTo(methodOn(MovieController.class).getMovieById(savedMovie.getId())).toUri())
                .body(movieResource);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityModel<?>> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        Optional<Movie> updated = movieService.updateMovie(id, movie);

        if (updated.isPresent()) {
            Movie updatedMovie = updated.get();

            EntityModel<Movie> movieResource = EntityModel.of(updatedMovie,
                    linkTo(methodOn(MovieController.class).getMovieById(updatedMovie.getId())).withSelfRel(),
                    linkTo(methodOn(MovieController.class).getAllMovies()).withRel("all-movies"));

            return ResponseEntity.ok(movieResource);
        } else {
            EntityModel<ErrorResponse> errorResource = EntityModel.of(
                    new ErrorResponse("Movie with ID " + id + " not found", 404));
            return ResponseEntity.status(404).body(errorResource);
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
