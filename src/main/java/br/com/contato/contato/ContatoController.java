package br.com.contato.contato;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.contato.dtos.ContatoDTO;
import br.com.contato.responses.ContatoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author  Romerito Alencar
 */

@Validated
@RestController
@RequestMapping("/api") 
@Api(value = "Rest API Contato")
@CrossOrigin(origins = "*")
public class ContatoController {

    private static final Logger LOGGER = LogManager.getLogger(ContatoController.class);
    
    @Autowired
    ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    /**
     * @param ContatoDTO
     * @return ContatoResponse
    */
    @ApiOperation(value = "Salvar contato")
        @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Contato criado", response = Contato.class),
        @ApiResponse(code = 400, message = "Erro ao criar contato")
    })
    @PostMapping(path = "/contato/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody @Valid ContatoDTO contatoDTO) {
        try {
            LOGGER.info("saving ... Contato {} " ,  contatoDTO.getName());
            Contato contato = new Contato();
            BeanUtils.copyProperties(contatoDTO, contato);
            contato = contatoService.save(contato);
            contato.add(linkTo(methodOn((ContatoController.class)).save(contatoDTO)).withSelfRel());
             LOGGER.info("Saved ... Contato {} " ,  contatoDTO.getName());
            return ContatoResponse.responseBuilder(HttpStatus.CREATED,  contato);
        } catch (Exception e) {
            LOGGER.info("Contato ... {} " , contatoDTO.getName());
            LOGGER.info("Error ... {} " , e.getMessage());
            return ContatoResponse.responseBuilder(HttpStatus.BAD_REQUEST, contatoDTO);
        }
    }

}
