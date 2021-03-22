package br.com.movie.apimovie.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.movie.apimovie.domain.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>{

	 public List<Movie> findByWinnerOrderByYearAsc(String winner);
}
