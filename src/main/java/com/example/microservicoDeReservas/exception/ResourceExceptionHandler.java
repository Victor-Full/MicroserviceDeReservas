package com.example.microservicoDeReservas.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<StandardError> validacao(ValidacaoException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(), // 400
                "Erro de Validação",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<StandardError> conflito(ConflitoException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.CONFLICT.value(), // 409
                "Conflito de Horário",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<StandardError> naoEncontrado(RecursoNaoEncontradoException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(), // 404
                "Recurso não encontrado",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}