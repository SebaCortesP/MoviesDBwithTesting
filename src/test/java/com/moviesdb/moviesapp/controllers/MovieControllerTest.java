package com.moviesdb.moviesapp.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviesdb.moviesapp.config.SecurityConfig;
import com.moviesdb.moviesapp.models.Movie;
import com.moviesdb.moviesapp.services.MovieService;

@WebMvcTest(MovieController.class)
@WithMockUser
@Import(SecurityConfig.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieServiceMock;

    private List<Movie> movies;

    @BeforeEach
    public void setUp() {
        Movie m1 = new Movie();
        m1.setTitle("Che copete");
        m1.setId(1L);
        Movie m2 = new Movie();
        m2.setTitle("Condorito");
        m2.setId(2L);
        Movie m3 = new Movie();
        m3.setTitle("Taxi para 3");
        m3.setId(3L);

        movies = Arrays.asList(m1, m2, m3);
    }

    @Test
    public void getAllMoviesTest() throws Exception {
        // arrange
        when(movieServiceMock.getAllMovies()).thenReturn(movies);

        // act & assert
        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.movieList", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.movieList[0].title", Matchers.is("Che copete")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.movieList[1].title", Matchers.is("Condorito")))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$._embedded.movieList[2].title", Matchers.is("Taxi para 3")));
    }

    @Test
    public void getMovieByIdTest() throws Exception {
        // arrange
        Movie movie = movies.get(0); // "Che copete", id = 1

        when(movieServiceMock.getMovieById(movie.getId())).thenReturn(Optional.of(movie));

        // act & assert
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/" + movie.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(movie.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is(movie.getTitle())));
    }

    @Test
    public void storeMovieTest() throws Exception {
        // arrange
        Movie movieToSave = new Movie();
        movieToSave.setTitle("Nuevo título");
        movieToSave.setDirector("Nuevo director");

        Movie savedMovie = new Movie();
        savedMovie.setId(1L);
        savedMovie.setTitle("Nuevo título");
        savedMovie.setDirector("Nuevo director");

        when(movieServiceMock.storeMovie(any(Movie.class))).thenReturn(Optional.of(savedMovie));

        // act & assert
        mockMvc.perform(MockMvcRequestBuilders.post("/movies/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(movieToSave)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", Matchers.containsString("/movies/1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Nuevo título"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.director").value("Nuevo director"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.all-movies.href").exists());
    }

}
