package com.example.microservicoDeReservas.controller;

import com.example.microservicoDeReservas.dto.ReservaDTO;
import com.example.microservicoDeReservas.model.entity.Reserva;
import com.example.microservicoDeReservas.model.service.ReservaService;
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
    public ResponseEntity<Reserva> criar(@RequestBody ReservaDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @RequestBody ReservaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/espaco/{espacoId}")
    public ResponseEntity<List<Reserva>> listarPorEspaco(@PathVariable long espacoId) {
        return ResponseEntity.ok(service.listarPorEspaco(espacoId));
    }

    @GetMapping("/alocadas")
    public ResponseEntity<List<Reserva>> listarAlocados() {
        return ResponseEntity.ok(service.findValid());
    }
}
