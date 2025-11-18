package com.example.microservicoDeReservas.service;

import com.example.microservicoDeReservas.dto.ReservaDTO;
import com.example.microservicoDeReservas.model.entity.Reserva;
import com.example.microservicoDeReservas.model.entity.StatusEspaco;
import com.example.microservicoDeReservas.model.entity.StatusReserva;
import com.example.microservicoDeReservas.model.repository.ReservaRepository;
import com.example.microservicoDeReservas.validation.ReservaValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final ReservaValidation validation ;

    public ReservaService(ReservaRepository repository, ReservaValidation validation){
        this.repository = repository;
        this.validation = validation;
    }

    public Reserva criar(ReservaDTO dto){
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

    public List<Reserva> listarTodas(){
        return repository.findAll();
    }

    public List<Reserva> listarPorEspaco(Long espacoId){
        return repository.findByEspacoId(espacoId);
    }
}