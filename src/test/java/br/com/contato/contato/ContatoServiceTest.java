package br.com.contato.contato;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.contato.dtos.ContatoDTO;


/**
 * @author  Romerito Alencar
 */

@SpringBootTest
@AutoConfigureMockMvc
class ContatoServiceTest {
    
    @Autowired
	private MockMvc mockMvc;

    @Mock
    private ContatoService contatoService;

    @Mock
    private ContatoRepository contatoRepository;

    @Autowired
    private ObjectMapper mapper;

    private Contato contato = new Contato();

    private Contato contatoErro = new Contato();

    private ContatoDTO contatoDTO;

    private ContatoDTO contatoDTOErro;

    private ContatoDTO contatoDTONovo;

    private Contato contatoNovo = new Contato();

    private static final String URI = "/api/contato/";
    
    @BeforeEach
    void setUp() {
        this.contatoDTONovo = ContatoDTO.builder()
                .name("Romerito")
                .phone("61995817743")
                .email("romerito.alencar@gmail.com")
                .subject("Teste de api")
                .message("Test Junit unit and Integration Mockito")
                .build();
        BeanUtils.copyProperties(contatoDTONovo, contatoNovo);

        this.contatoDTO = ContatoDTO.builder()
                .name("Romerito")
                .phone("61995817743")
                .email("romerito.alencar@gmail.com")
                .subject("Teste de api")
                .message("Test Junit unit and Integration Mockito")
                .build();
        BeanUtils.copyProperties(contatoDTO, contato);

        this.contatoDTOErro = ContatoDTO.builder()
                .phone("61995817743")
                .email("romerito.alencar@gmail.com")
                .subject("Teste de api")
                .message("Test Junit unit and Integration Mockito")
                .build();
        BeanUtils.copyProperties(contatoDTOErro, contatoErro);

        ContatoController contatoController = new ContatoController(contatoService);
        mockMvc = MockMvcBuilders.standaloneSetup(contatoController).build();
    }
    
    @Test
    void givenSaveContao_thenReturnContato() {
        Mockito.when(contatoRepository.save(contato)).thenReturn(contatoNovo);
        Assertions.assertEquals(contato, contatoNovo);
    }
    
    @Test
    void whenSaveContato_thenReturn_created() throws Exception {
        Mockito.when(contatoService.save(contato)).thenReturn(contatoNovo);
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(contato).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    void whenSaveContato_thenReturn_badRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(contatoErro).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }
            
}
