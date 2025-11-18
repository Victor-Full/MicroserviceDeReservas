package com.example.microservicoDeReservas.scheduler;

import com.example.microservicoDeReservas.model.service.ReservaService;
import org.springframework.scheduling.annotation.Scheduled;

public class ReservaScheduler {

    private final ReservaService reservaService;

    public ReservaScheduler(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @Scheduled(fixedRate = 60000)
    public void scheduled() {
        reservaService.atualizaStatus();
    }
}
