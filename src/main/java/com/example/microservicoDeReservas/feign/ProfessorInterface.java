package com.example.microservicoDeReservas.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client", url = "http://localhost:8083/professores")
public interface ProfessorInterface {

    @GetMapping("/exists/{id}")
    public Boolean existsId(@PathVariable Long id);
}
