package dh.meli.reviewtests.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class MyExceptionHandler {
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(PersonNotFoundException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .titulo("Person não encontrado")
                        .mensagem(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPersonParam.class)
    public ResponseEntity<ExceptionDetails> handlerInvalidPersonParam(InvalidPersonParam ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .titulo("Person invalid")
                        .mensagem(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }


}
