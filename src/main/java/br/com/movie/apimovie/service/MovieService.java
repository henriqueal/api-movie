package br.com.movie.apimovie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.movie.apimovie.domain.IntervaloPremio;
import br.com.movie.apimovie.domain.IntervaloPremioView;
import br.com.movie.apimovie.domain.IntervaloProdutor;
import br.com.movie.apimovie.domain.Movie;
import br.com.movie.apimovie.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	Integer minIntervalGlobal = null;
	Integer maxIntervalGlobal = null;

	public IntervaloPremioView buscaIntervaloDePremios() {

		IntervaloPremioView view = new IntervaloPremioView();
		List<IntervaloPremio> minList = new ArrayList<>();
		List<IntervaloPremio> maxList = new ArrayList<>();

		List<Movie> movies = movieRepository.findByWinnerOrderByYearAsc("yes");

		Map<String, IntervaloProdutor> map = criaMap(movies);

		for (Map.Entry<String, IntervaloProdutor> entry : map.entrySet()) {

			IntervaloProdutor intervaloProdutor = entry.getValue();

			minList = atualizaMinList(minList, intervaloProdutor);
			maxList = atualizaMaxList(maxList, intervaloProdutor);

		}

		view.setMin(minList);
		view.setMax(maxList);

		return view;

	}

	private List<IntervaloPremio> atualizaMaxList(List<IntervaloPremio> maxList, IntervaloProdutor intervaloProdutor) {
		if (intervaloProdutor.getMaxInterval() == maxIntervalGlobal) {
			IntervaloPremio intervaloPremioMax = new IntervaloPremio();
			intervaloPremioMax.setInterval(intervaloProdutor.getMaxInterval());
			intervaloPremioMax.setPreviousWin(intervaloProdutor.getMaxPreviousWin());
			intervaloPremioMax.setFollowingWin(intervaloProdutor.getMaxFollowingWin());
			intervaloPremioMax.setProducer(intervaloProdutor.getProducer());
			maxList.add(intervaloPremioMax);
		}
		return maxList;
	}

	private List<IntervaloPremio> atualizaMinList(List<IntervaloPremio> minList, IntervaloProdutor intervaloProdutor) {
		if (intervaloProdutor.getMinInterval() == minIntervalGlobal) {
			IntervaloPremio intervaloPremioMin = new IntervaloPremio();
			intervaloPremioMin.setInterval(intervaloProdutor.getMinInterval());
			intervaloPremioMin.setPreviousWin(intervaloProdutor.getMinPreviousWin());
			intervaloPremioMin.setFollowingWin(intervaloProdutor.getMinFollowingWin());
			intervaloPremioMin.setProducer(intervaloProdutor.getProducer());
			minList.add(intervaloPremioMin);
		}
		return minList;
	}

	private Map<String, IntervaloProdutor> criaMap(List<Movie> movies) {

		Map<String, IntervaloProdutor> map = new HashMap<>();

		for (Movie movie : movies) {

			IntervaloProdutor intervaloProdutor;

			if (map.get(movie.getProducers()) == null) {
				intervaloProdutor = inicializaIntervaloProdutor(movie);
			} else {

				intervaloProdutor = map.get(movie.getProducers());

				if (intervaloProdutor.getMinFollowingWin() == null) {
					intervaloProdutor = atualizaSegundoIntervalo(movie, intervaloProdutor);

				} else {
					intervaloProdutor = atualizaEnezimoIntervalo(movie, intervaloProdutor);
				}

				minIntervalGlobal = atualizaMinIntervalGlobal(minIntervalGlobal, intervaloProdutor);

				maxIntervalGlobal = atualizaMaxIntervalGlobal(maxIntervalGlobal, intervaloProdutor);

			}
			map.put(movie.getProducers(), intervaloProdutor);
		}

		return map;
	}

	private IntervaloProdutor atualizaEnezimoIntervalo(Movie movie, IntervaloProdutor intervaloProdutor) {
		if ((movie.getYear() - intervaloProdutor.getMinFollowingWin()) < intervaloProdutor.getMinInterval()) {
			intervaloProdutor.setMinPreviousWin(intervaloProdutor.getMinFollowingWin());
			intervaloProdutor.setMinFollowingWin(movie.getYear());
			intervaloProdutor
					.setMinInterval(intervaloProdutor.getMinFollowingWin() - intervaloProdutor.getMinPreviousWin());
		}

		if ((movie.getYear() - intervaloProdutor.getMaxFollowingWin()) > intervaloProdutor.getMaxInterval()) {
			intervaloProdutor.setMaxPreviousWin(intervaloProdutor.getMaxFollowingWin());
			intervaloProdutor.setMaxFollowingWin(movie.getYear());
			intervaloProdutor
					.setMaxInterval(intervaloProdutor.getMaxFollowingWin() - intervaloProdutor.getMaxPreviousWin());
		}

		return intervaloProdutor;
	}

	private IntervaloProdutor atualizaSegundoIntervalo(Movie movie, IntervaloProdutor intervaloProdutor) {
		intervaloProdutor.setMinFollowingWin(movie.getYear());
		intervaloProdutor.setMinInterval(movie.getYear() - intervaloProdutor.getMinPreviousWin());

		intervaloProdutor.setMaxFollowingWin(movie.getYear());
		intervaloProdutor.setMaxInterval(movie.getYear() - intervaloProdutor.getMaxPreviousWin());

		return intervaloProdutor;
	}

	private IntervaloProdutor inicializaIntervaloProdutor(Movie movie) {
		IntervaloProdutor intervaloProdutor = new IntervaloProdutor();
		intervaloProdutor.setMinPreviousWin(movie.getYear());
		intervaloProdutor.setMaxPreviousWin(movie.getYear());
		intervaloProdutor.setProducer(movie.getProducers());
		return intervaloProdutor;
	}

	private Integer atualizaMaxIntervalGlobal(Integer maxIntervalGlobal, IntervaloProdutor intervaloProdutor) {
		if (maxIntervalGlobal == null || intervaloProdutor.getMaxInterval() > maxIntervalGlobal) {
			maxIntervalGlobal = intervaloProdutor.getMaxInterval();
		}
		return maxIntervalGlobal;
	}

	private Integer atualizaMinIntervalGlobal(Integer minIntervalGlobal, IntervaloProdutor intervaloProdutor) {

		if (minIntervalGlobal == null || intervaloProdutor.getMinInterval() < minIntervalGlobal) {
			minIntervalGlobal = intervaloProdutor.getMinInterval();
		}
		return minIntervalGlobal;
	}

}
