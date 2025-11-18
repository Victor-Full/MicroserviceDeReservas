package com.example.microservicoDeReservas.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long espacoId;

    @Column(nullable = false)
    private Long professorId;

    private LocalDate dataReserva;

    private LocalTime horaInicial;
    private LocalTime horaFinal;

    @Enumerated(EnumType.STRING)
    private StatusReserva statusReserva;

    @Enumerated(EnumType.STRING)
    private StatusEspaco statusEspaco;

}