package com.example.microservicoDeReservas.model.exception;

public class NaoExisteException extends RuntimeException {
    public NaoExisteException(String message) {
        super(message);
    }
}
