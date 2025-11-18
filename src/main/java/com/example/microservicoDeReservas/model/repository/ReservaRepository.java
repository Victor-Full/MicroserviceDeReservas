package com.example.microservicoDeReservas.model.repository;

import com.example.microservicoDeReservas.model.entity.Reserva;
import com.example.microservicoDeReservas.model.entity.StatusEspaco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByEspacoId(Long espacoId);

    List<Reserva> findByEspacoIdAndDataReservaAndStatusEspaco(Long espacoId, LocalDate dataReserva, StatusEspaco statusEspaco);
}
