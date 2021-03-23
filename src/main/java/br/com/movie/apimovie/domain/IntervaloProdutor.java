package br.com.movie.apimovie.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntervaloProdutor {
	
	String producer;
	
	Integer minInterval;
	Integer minPreviousWin;
	Integer minFollowingWin;
	
	Integer maxInterval;
	Integer maxPreviousWin;
	Integer maxFollowingWin;

}
