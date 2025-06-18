package ar.edu.palermo.microservicios.ventasservice.controller;

import ar.edu.palermo.microservicios.ventasservice.model.DatosFacturaDTO;
import ar.edu.palermo.microservicios.ventasservice.service.VentasServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ventas")
public class VentasController {

    private final VentasServiceImpl ventasService;

    @GetMapping("/facturar")
    public ResponseEntity<Void> registrarVenta(@RequestBody @Valid DatosFacturaDTO facturaDto) {
        ventasService.realizarVenta(facturaDto);
        return ResponseEntity.ok().build();
    }

}
