package com.moviesdb.moviesapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.moviesdb.moviesapp.models.Movie;
import com.moviesdb.moviesapp.repositories.MovieRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieGlobalTest {
    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        // Crear las películas
        Movie movie1 = new Movie();
        movie1.setTitle("Titanic");
        movie1.setDescription("Romance en un barco");
        movie1.setDirector("James Cameron");
        movie1.setYear(1997);
        movie1.setGenre("Drama");
        movie1.setSynopsis("Una historia de amor en el Titanic");

        Movie movie2 = new Movie();
        movie2.setTitle("Matrix");
        movie2.setDescription("Ciencia ficción y acción");
        movie2.setDirector("Lana Wachowski");
        movie2.setYear(1999);
        movie2.setGenre("Sci-Fi");
        movie2.setSynopsis("La realidad no es lo que parece");

        Movie movie3 = new Movie();
        movie3.setTitle("Shrek");
        movie3.setDescription("Un ogro con buen corazón");
        movie3.setDirector("Andrew Adamson");
        movie3.setYear(2001);
        movie3.setGenre("Animación");
        movie3.setSynopsis("Aventura de un ogro y su burro");

        movieRepository.saveAll(List.of(movie1, movie2, movie3));
    }

    @Test
    public void testCantidadPeliculas() {
        List<Movie> movies = movieRepository.findAll();

        // Imprimir las películas para verificar
        movies.forEach(System.out::println);

        // Verificar que el tamaño sea 3
        assertThat(movies).hasSize(3);
    }

    @Test
    public void storeMovieTest() {
        Movie movie = new Movie();
        movie.setTitle("Che Copete");
        movie.setDescription("Pelicula demo");
        movie.setDirector("Martin Lopez");
        movie.setYear(1999);

        Movie result = movieRepository.save(movie);

        assertNotNull(result.getId());
        assertEquals("Che Copete", result.getTitle());
    }
}
