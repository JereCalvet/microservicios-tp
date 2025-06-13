package ar.edu.palermo.microservicios.stockservice.controller;

import ar.edu.palermo.microservicios.stockservice.model.StockResponseDTO;
import ar.edu.palermo.microservicios.stockservice.service.AlmacenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/almacenes")
public class AlmacenController {

    private final AlmacenService almacenSvc;

    @GetMapping("/check-stock/{idAlmacen}/vehiculo/{idVehiculo}")
    public ResponseEntity<StockResponseDTO> checkStock(
            @PathVariable("idAlmacen") Long idAlmacen,
            @PathVariable("idVehiculo") Long idVehiculo) {

        return null;
    }
}
