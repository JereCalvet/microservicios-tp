package ar.edu.palermo.microservicios.ventasservice.integration;

import ar.edu.palermo.microservicios.ventasservice.model.StockVerifyAvailabilityResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-service", path = "/api/v1/almacenes")
public interface StockClient {

    @GetMapping("/verify-availability/{idSucursal}/vehiculo/{idVehiculo}")
    StockVerifyAvailabilityResponseDTO verifyAvailability(
            @PathVariable("idSucursal") Long idSucursal,
            @PathVariable("idVehiculo") Long idVehiculo,
            @RequestParam(value = "cantidad", defaultValue = "1") Integer cantidad
    );


}
