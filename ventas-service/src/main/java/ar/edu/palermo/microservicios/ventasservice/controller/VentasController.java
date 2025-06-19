package ar.edu.palermo.microservicios.ventasservice.controller;

import ar.edu.palermo.microservicios.ventasservice.model.DatosFacturaDTO;
import ar.edu.palermo.microservicios.ventasservice.model.VentaResponseDTO;
import ar.edu.palermo.microservicios.ventasservice.service.VentasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ventas")
public class VentasController {

    private final VentasService ventasService;

    @PostMapping("/facturar")
    public ResponseEntity<VentaResponseDTO> registrarVenta(@RequestBody @Valid DatosFacturaDTO facturaDto) {
        VentaResponseDTO savedEntity = ventasService.realizarVenta(facturaDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/ventas/{id}")
                .buildAndExpand(savedEntity.id())
                .toUri();

        return ResponseEntity.created(location).body(savedEntity);
    }

    @GetMapping()
    public ResponseEntity<List<VentaResponseDTO>> getVentas() {
        return ResponseEntity.ok(ventasService.findAll());
    }

    @GetMapping("/patente/{patenteVehiculo}")
    public ResponseEntity<VentaResponseDTO> getVentaByPatente(@PathVariable("patenteVehiculo") String patenteVehiculo) {
        return ResponseEntity.ok(ventasService.findByPatenteVehiculo(patenteVehiculo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> getVentaById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ventasService.findById(id));
    }
}
