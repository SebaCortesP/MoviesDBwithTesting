package com.moviesdb.moviesapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.moviesdb.moviesapp.models.Movie;
import com.moviesdb.moviesapp.repositories.MovieRepository;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void storeMovieTest() {
        Movie m1 = new Movie(); 
        m1.setTitle("Titanic");
        m1.setDirector("Seba Cortes");
        m1.setYear(1992);
        m1.setGenre("Terror");
        Movie result = movieRepository.save(m1);
        assertNotNull(result.getId());
        assertEquals("Titanic", result.getTitle());
    }

    @Test
    void getMovieByIdTest(){
        Movie m1 = new Movie(); 
        m1.setTitle("Titanic");
        m1.setDirector("Seba Cortes");
        m1.setYear(1992);
        m1.setGenre("Terror");
        Movie result = movieRepository.save(m1);
        var foundMovie = movieRepository.findById(result.getId());

        assertTrue(foundMovie.isPresent(), "La película debería existir");
        assertEquals(1992, foundMovie.get().getYear(), "El año no coincide");
        assertEquals("Titanic", foundMovie.get().getTitle(), "El título no coincide");
    }
}
