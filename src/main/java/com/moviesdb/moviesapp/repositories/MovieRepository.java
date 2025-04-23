package com.moviesdb.moviesapp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.moviesdb.moviesapp.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
