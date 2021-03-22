package br.com.movie.apimovie.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntervaloPremioView {

	List<IntervaloPremio> min;
	List<IntervaloPremio> max;
}
