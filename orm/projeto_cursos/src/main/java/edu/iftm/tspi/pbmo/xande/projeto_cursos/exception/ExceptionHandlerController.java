package edu.iftm.tspi.pbmo.xande.projeto_cursos.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.iftm.tspi.pbmo.xande.projeto_cursos.dto.ErroDTO;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroDTO> tratarNaoEncontrado(RecursoNaoEncontradoException e) {
        ErroDTO erro = ErroDTO.builder()
                            .msg(e.getMessage())
                            .data(LocalDateTime.now())
                            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
