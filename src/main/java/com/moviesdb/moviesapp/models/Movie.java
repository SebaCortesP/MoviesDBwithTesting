package com.moviesdb.moviesapp.models;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Column(name = "id")
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "year")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para 'description'
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter y Setter para 'director'
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    // Getter y Setter para 'genre'
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Getter y Setter para 'synopsis'
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    // Getter y Setter para 'title'
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter y Setter para 'year'
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

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
