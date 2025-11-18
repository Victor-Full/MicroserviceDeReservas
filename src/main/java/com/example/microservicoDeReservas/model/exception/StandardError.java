package com.example.microservicoDeReservas.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
@AllArgsConstructor
public class StandardError {

    private Instant timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;
}
