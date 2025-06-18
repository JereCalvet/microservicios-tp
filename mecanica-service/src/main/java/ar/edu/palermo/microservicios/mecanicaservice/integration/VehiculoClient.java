package ar.edu.palermo.microservicios.mecanicaservice.integration;

import ar.edu.palermo.microservicios.mecanicaservice.model.VehiculoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "vehiculos-service", path = "/api/v1/vehiculos")
public interface VehiculoClient {

    @GetMapping("/{id}")
    VehiculoResponseDTO getVehiculoById(@PathVariable("id") Long id);
}
