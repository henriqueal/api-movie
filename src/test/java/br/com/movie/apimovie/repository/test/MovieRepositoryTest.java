package br.com.movie.apimovie.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.movie.apimovie.domain.Movie;
import br.com.movie.apimovie.repository.MovieRepository;

//@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application.properties")
public class MovieRepositoryTest {
	

    @Autowired
    private MovieRepository movieRepository;
	
    @Test
    public void deve_procurar_pessoa_pelo_cpf() throws Exception {
        List<Movie> movies = movieRepository.findByWinnerOrderByYearAsc("yes");
    	assertThat(1).isEqualTo(1);
        assertThat(movies.get(0).getYear()).isEqualTo(1980);
        //assertThat(pessoa.getNome()).isEqualTo("Cauê");
        //assertThat(pessoa.getCpf()).isEqualTo("38767897100");
    }

}
