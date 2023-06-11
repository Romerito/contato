package br.com.contato.contato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.contato.dtos.EmailDTO;

/**
 * @author  Romerito Alencar
 */

@Service
public class ContatoService {
    
    @Autowired
    ContatoRepository contatoRepository;
    
    RestTemplate restTemplate = new RestTemplate();

    private static String url = "http://localhost:8082/api/email/";
    private static String emailFrom  = "romerito.alencar@gmail.com";
    
    public Contato save(Contato contato){
      
        restTemplate = new RestTemplate();
        
        EmailDTO emailDTOGet = EmailDTO.builder()
                .ownerRef(contato.getName())
                .emailFrom(emailFrom)
                .emailTo(emailFrom)
                .subject(contato.getSubject())
                .text(contato.getName()  +" - "+ contato.getPhone()  +" - "+ contato.getEmail() +" \n \r\n" + contato.getMessage())
                .build();

        restTemplate.postForEntity(url, emailDTOGet, String.class);
        EmailDTO emailDTOSend = EmailDTO.builder()
                .ownerRef(contato.getName())
                .emailFrom(emailFrom)
                .emailTo(contato.getEmail())
                .subject("Alencar Web")
                .text("Olá sou Romerito. Já recebi seu email e estou analisando. Em breve entrarei encontato. \n \r\n" + "Obrigado pela preferência. \n \r\n" + "Atenciosamente,")
                .build();
        
        restTemplate.postForEntity(url,emailDTOSend, String.class);
        
        return  contatoRepository.save(contato);
     }

}
