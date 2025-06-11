package ar.edu.palermo.microservicios.vehiculosservice.controller;

import ar.edu.palermo.microservicios.vehiculosservice.model.VehiculoCreateDTO;
import ar.edu.palermo.microservicios.vehiculosservice.model.VehiculoPatchDTO;
import ar.edu.palermo.microservicios.vehiculosservice.model.VehiculoResponseDTO;
import ar.edu.palermo.microservicios.vehiculosservice.model.VehiculoUpdateDTO;
import ar.edu.palermo.microservicios.vehiculosservice.service.VehiculoService;
import ar.edu.palermo.microservicios.vehiculosservice.service.VehiculoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoSvc;

    @PostMapping
    public ResponseEntity<VehiculoResponseDTO> createEvent(@RequestBody @Valid VehiculoCreateDTO createDto) {
        VehiculoResponseDTO savedEntity = vehiculoSvc.save(createDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.id())
                .toUri();

        return ResponseEntity.created(location).body(savedEntity);
    }

    @GetMapping()
    public ResponseEntity<List<VehiculoResponseDTO>> getEvents() {
        return ResponseEntity.ok(vehiculoSvc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> getVehiculoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vehiculoSvc.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> updateEvent(@PathVariable("id") Long id, @RequestBody @Valid VehiculoUpdateDTO updateDto) {
        return ResponseEntity.ok(vehiculoSvc.update(id, updateDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> patchEvent(@PathVariable("id") Long id, @RequestBody @Valid VehiculoPatchDTO patchDto) {
        return ResponseEntity.ok(vehiculoSvc.update(id, patchDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id) {
        vehiculoSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
