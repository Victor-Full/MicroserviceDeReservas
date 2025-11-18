package com.example.microservicoDeReservas.model.validation;

import com.example.microservicoDeReservas.dto.ReservaDTO;
import com.example.microservicoDeReservas.model.exception.ConflitoException;
import com.example.microservicoDeReservas.model.exception.ValidacaoException;
import com.example.microservicoDeReservas.model.entity.Reserva;
import com.example.microservicoDeReservas.model.entity.StatusEspaco;
import com.example.microservicoDeReservas.model.repository.ReservaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class ReservaValidation {

    private final ReservaRepository repository;

    public ReservaValidation(ReservaRepository repository) {
        this.repository = repository;
    }

    public void validar(ReservaDTO dto) {
        validarHorariosPermitidos(dto);

        validarConflitoDeHorario(dto);
    }

    private void validarHorariosPermitidos(ReservaDTO dto) {
        LocalTime inicioPermitido = LocalTime.of(7, 0);
        LocalTime fimPermitido = LocalTime.of(21, 40);

        if (dto.horaInicial().isAfter(dto.horaFinal())) {
            throw new ValidacaoException("A hora inicial não pode ser após a hora final.");
        }

        if (dto.horaInicial().equals(dto.horaFinal())) {
            throw new ValidacaoException("A reserva precisa ter alguma duração.");
        }

        if (dto.horaInicial().isBefore(inicioPermitido) || dto.horaFinal().isAfter(fimPermitido)) {
            throw new ValidacaoException("As reservas devem ser feitas entre 07:00 e 21:40.");
        }
    }

    private void validarConflitoDeHorario(ReservaDTO dto) {
        List<Reserva> reservasExistentes = repository.findByEspacoIdAndDataReservaAndStatusEspaco(
                dto.espacoId(),
                dto.dataReserva(),
                StatusEspaco.ALOCADO
        );

        for (Reserva reserva : reservasExistentes) {
            if (dto.id() != null && dto.id().equals(reserva.getId())) {
                continue;
            }

            if (horarioConflita(dto.horaInicial(), dto.horaFinal(), reserva.getHoraInicial(), reserva.getHoraFinal())) {
                throw new ConflitoException("Conflito de horário! O espaço já está alocado das "
                        + reserva.getHoraInicial() + " às " + reserva.getHoraFinal());
            }
        }
    }

    private boolean horarioConflita(LocalTime inicio1, LocalTime fim1, LocalTime inicio2, LocalTime fim2 ){
        return inicio1.isBefore(fim2) && fim1.isAfter(inicio2);
    }

}