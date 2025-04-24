package com.moviesdb.moviesapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.moviesdb.moviesapp.models.Movie;
import com.moviesdb.moviesapp.repositories.MovieRepository;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @InjectMocks
    private MovieService  movieService;

    @Mock
    private MovieRepository movieRepositoryMock;

    @Test
    public void storeMovieTest(){
        // Arrange
        Movie movie = new Movie();
        movie.setTitle("Che Copete");
        // simulamos que guardamos en el repository la data
        when(movieRepositoryMock.save(any())).thenReturn(movie);

        // act
        Optional<Movie> result = movieService.storeMovie(movie);

        // Asserts
        assertEquals("Che Copete", result.get().getTitle());
    }
}
