package ar.edu.palermo.microservicios.ventasservice.integration;

import ar.edu.palermo.microservicios.ventasservice.model.StockStatusResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-service", path = "/api/v1/almacenes")
public interface StockClient {
    @GetMapping("/check-stock/{idAlmacen}/vehiculo/{idVehiculo}")
    StockStatusResponseDTO checkStock(
            @PathVariable Long idAlmacen,
            @PathVariable Long idVehiculo
    );
}
