package br.com.contato.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author  Romerito Alencar
 */

public class ContatoResponse {

        private ContatoResponse() {}

        public static ResponseEntity<Object> responseBuilder(HttpStatus httpStatus, Object responObject) {
                Map<String, Object> response = new HashMap<>();
                response.put("httpStatus", httpStatus);
                response.put("data", responObject);
                return new ResponseEntity<>(response, httpStatus);
        }
}
