package com.example.microservicoDeReservas.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Espaco", url = "http://localhost:8088/api/espacos")
public interface EspacoInterface {

    @GetMapping("/exists/{id}")
    public boolean existsIdEspaco(@PathVariable Long id);
}
