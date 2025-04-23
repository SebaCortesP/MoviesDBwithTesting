package com.moviesdb.moviesapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.moviesdb.moviesapp.models.Movie;
import com.moviesdb.moviesapp.repositories.MovieRepository;
import com.moviesdb.moviesapp.services.MovieService;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieRepositoryTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void testGetAllMovies() {
        Movie m1 = new Movie(); m1.setTitle("Titanic");
        Movie m2 = new Movie(); m2.setTitle("Matrix");

        when(movieRepository.findAll()).thenReturn(Arrays.asList(m1, m2));

        List<Movie> movies = movieService.getAllMovies();

        assertEquals(2, movies.size());
        verify(movieRepository, times(1)).findAll();
    }
}
