package br.com.movie.apimovie.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntervaloPremio {

	String producer;
	Integer interval;
	Integer previousWin;
	Integer followingWin;
}
