package com.example.microservicoDeReservas.model.service;

import com.example.microservicoDeReservas.dto.ReservaDTO;
import com.example.microservicoDeReservas.model.entity.Reserva;
import com.example.microservicoDeReservas.model.entity.StatusEspaco;
import com.example.microservicoDeReservas.model.entity.StatusReserva;
import com.example.microservicoDeReservas.model.exception.RecursoNaoEncontradoException;
import com.example.microservicoDeReservas.model.repository.ReservaRepository;
import com.example.microservicoDeReservas.model.validation.ReservaValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final ReservaValidation validation;

    public ReservaService(ReservaRepository repository, ReservaValidation validation) {
        this.repository = repository;
        this.validation = validation;
    }

    public Reserva findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Reserva com ID " + id + " não encontrada."));
    }

    public Reserva criar(ReservaDTO dto) {
        validation.validaEspaco(dto);
        validation.validaProfessor(dto);
        validation.validar(dto);

        Reserva reserva = new Reserva();

        reserva.setEspacoId(dto.espacoId());

        reserva.setProfessorId(dto.professorId());

        reserva.setDataReserva(dto.dataReserva());

        reserva.setHoraInicial(dto.horaInicial());
        reserva.setHoraFinal(dto.horaFinal());

        reserva.setStatusReserva(StatusReserva.APROVADO);
        reserva.setStatusEspaco(StatusEspaco.ALOCADO);

        return repository.save(reserva);
    }

    public Reserva update(Long id, ReservaDTO dto) {
        Reserva reservaExistente = findById(id);

        validation.validaEspaco(dto);
        validation.validaProfessor(dto);
        validation.validar(dto);

        reservaExistente.setEspacoId(dto.espacoId());
        reservaExistente.setProfessorId(dto.professorId());
        reservaExistente.setDataReserva(dto.dataReserva());
        reservaExistente.setHoraInicial(dto.horaInicial());
        reservaExistente.setHoraFinal(dto.horaFinal());

        return repository.save(reservaExistente);
    }

    public void delete(Long id) {
        Reserva reservaExistente = findById(id);
        repository.delete(reservaExistente);
    }

    public List<Reserva> listarTodas() {
        return repository.findAll();
    }

    public List<Reserva> listarPorEspaco(Long espacoId) {
        return repository.findByEspacoId(espacoId);
    }

    public List<Reserva> findValid() {
        List<Reserva> listaAprovados = new ArrayList<>();
        for (Reserva reserva : repository.findAll()) {
            if (reserva.getStatusEspaco() == StatusEspaco.ALOCADO)
                listaAprovados.add(reserva);
        }
        return listaAprovados;
    }

    public void atualizaStatus() {
        LocalTime tempoAgora = LocalTime.now();
        LocalDate dataAgora = LocalDate.now();

        for (Reserva r : findValid()) {
            if (r.getDataReserva().isEqual(dataAgora) && tempoAgora.isAfter(r.getHoraFinal()))
                r.setStatusEspaco(StatusEspaco.LIVRE);

            if (r.getDataReserva().isEqual(dataAgora) && !tempoAgora.isBefore(r.getHoraInicial())
                    && !tempoAgora.isAfter(r.getHoraFinal())) {
                r.setStatusEspaco(StatusEspaco.ALOCADO);
                r.setStatusReserva(StatusReserva.APROVADO);
            }

            repository.save(r);

        }
    }
}
