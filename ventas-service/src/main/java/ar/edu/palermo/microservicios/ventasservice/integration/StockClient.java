package ar.edu.palermo.microservicios.ventasservice.integration;

import ar.edu.palermo.microservicios.ventasservice.model.StockRequestDTO;
import ar.edu.palermo.microservicios.ventasservice.model.StockRequestResponseDTO;
import ar.edu.palermo.microservicios.ventasservice.model.StockStatusResponseDTO;
import ar.edu.palermo.microservicios.ventasservice.model.StockVerifyAvailabilityResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "stock-service", path = "/api/v1/almacenes")
public interface StockClient {

    @GetMapping("/verify-availability/{idSucursal}/vehiculo/{idVehiculo}")
    StockVerifyAvailabilityResponseDTO verifyAvailability(
            @PathVariable("idSucursal") Long idSucursal,
            @PathVariable("idVehiculo") Long idVehiculo,
            @RequestParam(value = "cantidad", defaultValue = "1") Integer cantidad
    );

    @PostMapping("/stock-request")
    StockRequestResponseDTO stockRequest(@RequestBody StockRequestDTO dto);


    @PostMapping("/remove-stock/{idAlmacen}/vehiculo/{idVehiculo}")
    StockStatusResponseDTO removeStock(
            @PathVariable("idAlmacen") Long idAlmacen,
            @PathVariable("idVehiculo") Long idVehiculo,
            @RequestParam("cantidad") Integer cantidad
    );
}
