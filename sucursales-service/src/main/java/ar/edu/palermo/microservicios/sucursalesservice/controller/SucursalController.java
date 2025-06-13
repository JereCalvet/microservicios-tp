package ar.edu.palermo.microservicios.sucursalesservice.controller;

import ar.edu.palermo.microservicios.sucursalesservice.model.*;
import ar.edu.palermo.microservicios.sucursalesservice.service.SucursalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    private final SucursalService sucursalSvc;

    @PostMapping
    public ResponseEntity<SucursalResponseDTO> createSucursal(@RequestBody @Valid SucursalCreateDTO createDto) {
        SucursalResponseDTO savedEntity = sucursalSvc.save(createDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.id())
                .toUri();

        return ResponseEntity.created(location).body(savedEntity);
    }

    @GetMapping()
    public ResponseEntity<List<SucursalResponseDTO>> getSucursales() {
        return ResponseEntity.ok(sucursalSvc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalResponseDTO> getSucursalById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sucursalSvc.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalResponseDTO> updateSucursal(@PathVariable("id") Long id, @RequestBody @Valid SucursalUpdateDTO updateDto) {
        return ResponseEntity.ok(sucursalSvc.update(id, updateDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SucursalResponseDTO> patchSucursal(@PathVariable("id") Long id, @RequestBody @Valid SucursalPatchDTO patchDto) {
        return ResponseEntity.ok(sucursalSvc.update(id, patchDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable("id") Long id) {
        sucursalSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
