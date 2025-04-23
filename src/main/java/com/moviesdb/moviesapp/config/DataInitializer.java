package com.moviesdb.moviesapp.config;

import com.moviesdb.moviesapp.models.Movie;
import com.moviesdb.moviesapp.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initMovies(MovieRepository movieRepository) {
        return args -> {
            if (movieRepository.count() == 0) {
                List<Movie> movies = List.of(
                    new Movie(
                        "Machuca",
                        "Drama histórico en tiempos del golpe militar",
                        2004,
                        "Andrés Wood",
                        "Drama",
                        "Dos niños de clases sociales diferentes forman una amistad en un colegio religioso"
                    ),
                    new Movie(
                        "No",
                        "Campaña del plebiscito de 1988",
                        2012,
                        "Pablo Larraín",
                        "Drama político",
                        "Un publicista diseña una campaña para votar NO a la dictadura de Pinochet"
                    ),
                    new Movie(
                        "Una mujer fantástica",
                        "Retrato de una mujer trans enfrentando la discriminación",
                        2017,
                        "Sebastián Lelio",
                        "Drama",
                        "Marina debe luchar por su dignidad tras la muerte de su pareja"
                    ),
                    new Movie(
                        "Tony Manero",
                        "Obsesión por Travolta en plena dictadura",
                        2008,
                        "Pablo Larraín",
                        "Thriller psicológico",
                        "Raúl sueña con ser Tony Manero mientras comete crímenes en secreto"
                    ),
                    new Movie(
                        "El club",
                        "Sacerdotes en retiro forzado",
                        2015,
                        "Pablo Larraín",
                        "Drama",
                        "Cuatro curas viven en aislamiento tras escándalos, hasta que un quinto llega y rompe el equilibrio"
                    )
                );
                movieRepository.saveAll(movies);
                System.out.println("películas insertadas con éxito.");
            }
        };
    }
}
