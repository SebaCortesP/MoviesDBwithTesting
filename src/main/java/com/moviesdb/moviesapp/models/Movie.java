package com.moviesdb.moviesapp.models;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "movies")
public class Movie extends RepresentationModel<Movie>{    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Column(name = "id")
    @Column(name = "title", nullable = false)
    @NotBlank(message = "Title is required")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "\"year\"")  
    private Integer year;
    @Column(name = "director")
    private String director;
    @Column(name = "genre")
    private String genre;
    @Column(name = "synopsis")
    private String synopsis;

    public Movie(String title, String description, Integer year, String director, String genre, String synopsis) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.director = director;
        this.genre = genre;
        this.synopsis = synopsis;
    }

    public Movie() {}

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", director='" + director + '\'' +
            ", year=" + year +
            '}';
    }
}
