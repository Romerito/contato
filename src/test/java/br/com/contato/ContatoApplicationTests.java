package br.com.contato;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author  Romerito Alencar
 */

@SpringBootTest
class ContatoApplicationTests {
	
	@Autowired
	ContatoApplication contatoApplication;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(contatoApplication);
	}

}
