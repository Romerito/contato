package br.com.contato.contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author  Romerito Alencar
 */

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

 
}