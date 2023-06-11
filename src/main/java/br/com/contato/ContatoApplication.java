package br.com.contato;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author  Romerito Alencar
 */

@SpringBootApplication
@EnableWebMvc
public class ContatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContatoApplication.class, args);
	}

}
