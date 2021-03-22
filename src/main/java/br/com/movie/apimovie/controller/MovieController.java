package br.com.movie.apimovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movie.apimovie.domain.IntervaloPremioView;
import br.com.movie.apimovie.service.MovieService;

@RestController
@RequestMapping(value="/")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping(value= "/buscaIntervaloDePremios")
	public ResponseEntity<IntervaloPremioView> buscaIntervaloDePremios() {
		return ResponseEntity.ok(movieService.buscaIntervaloDePremios());
	}
	
}
