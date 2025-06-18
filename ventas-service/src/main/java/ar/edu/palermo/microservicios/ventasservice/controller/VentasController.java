package ar.edu.palermo.microservicios.ventasservice.controller;

import ar.edu.palermo.microservicios.ventasservice.service.VentasServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ventas")
public class VentasController {

    private final VentasServiceImpl ventasService;

    @GetMapping("/test")
    public ResponseEntity<Void> test() {
        ventasService.realizarVenta(1L, 1L, 1L, 7);
        return ResponseEntity.ok().build();
    }

}
