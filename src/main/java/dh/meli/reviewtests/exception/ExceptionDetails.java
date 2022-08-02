package dh.meli.reviewtests.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ExceptionDetails {
    private String titulo;
    private String mensagem;
    private LocalDateTime timestamp;
}
