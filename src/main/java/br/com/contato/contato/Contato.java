package br.com.contato.contato;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author  Romerito Alencar
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contato")
public class Contato  extends RepresentationModel<Contato> {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String subject;
    private String message;
}
