package com.example.microservicoDeReservas.controller;

import com.example.microservicoDeReservas.dto.ReservaDTO;
import com.example.microservicoDeReservas.model.entity.Reserva;
import com.example.microservicoDeReservas.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody ReservaDTO dto){
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listar(){
    return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/espaco/{espacoId}")
    public ResponseEntity<List<Reserva>> listarPorEspaco(@PathVariable long espacoId){
        return ResponseEntity.ok(service.listarPorEspaco(espacoId));
    }
}

