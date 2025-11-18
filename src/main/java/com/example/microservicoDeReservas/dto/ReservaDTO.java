package com.example.microservicoDeReservas.dto;

import com.example.microservicoDeReservas.model.entity.StatusEspaco;
import com.example.microservicoDeReservas.model.entity.StatusReserva;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaDTO(
        Long id,

        @NotNull
        Long espacoId,

        @NotNull
        Long professorId,

        @NotNull
        LocalDate dataReserva,

        @NotNull
        LocalTime horaInicial,
        @NotNull
        LocalTime horaFinal,

        StatusReserva statusReserva,
        StatusEspaco statusEspaco

) {}