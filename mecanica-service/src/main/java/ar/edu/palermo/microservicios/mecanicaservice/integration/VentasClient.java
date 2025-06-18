package ar.edu.palermo.microservicios.mecanicaservice.integration;

import ar.edu.palermo.microservicios.mecanicaservice.model.VentaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ventas-service", path = "/api/v1/ventas")
public interface VentasClient {

    @GetMapping("/patente/{patenteVehiculo}")
    VentaResponseDTO getVentaByPatente(@PathVariable("patenteVehiculo") String patenteVehiculo);
}