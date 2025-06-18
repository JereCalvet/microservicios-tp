package ar.edu.palermo.microservicios.mecanicaservice.controller;

import ar.edu.palermo.microservicios.mecanicaservice.model.ServicioMecanicoCreateDTO;
import ar.edu.palermo.microservicios.mecanicaservice.model.ServicioMecanicoPatchDTO;
import ar.edu.palermo.microservicios.mecanicaservice.model.ServicioMecanicoResponseDTO;
import ar.edu.palermo.microservicios.mecanicaservice.model.ServicioMecanicoUpdateDTO;
import ar.edu.palermo.microservicios.mecanicaservice.service.ServicioMecanicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/mecanica")
public class ServicioMecanicoController {

    private final ServicioMecanicoService servicioMecanicoSvc;

    @PostMapping
    public ResponseEntity<ServicioMecanicoResponseDTO> createServicioMecanico(@RequestBody @Valid ServicioMecanicoCreateDTO createDto) {
        ServicioMecanicoResponseDTO savedEntity = servicioMecanicoSvc.save(createDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.id())
                .toUri();

        return ResponseEntity.created(location).body(savedEntity);
    }

    @GetMapping()
    public ResponseEntity<List<ServicioMecanicoResponseDTO>> getServiciosMecanicos() {
        return ResponseEntity.ok(servicioMecanicoSvc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioMecanicoResponseDTO> getServicioMecanicoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(servicioMecanicoSvc.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioMecanicoResponseDTO> updateServicioMecanico(@PathVariable("id") Long id, @RequestBody @Valid ServicioMecanicoUpdateDTO updateDto) {
        return ResponseEntity.ok(servicioMecanicoSvc.update(id, updateDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServicioMecanicoResponseDTO> patchServicioMecanico(@PathVariable("id") Long id, @RequestBody @Valid ServicioMecanicoPatchDTO patchDto) {
        return ResponseEntity.ok(servicioMecanicoSvc.update(id, patchDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicioMecanico(@PathVariable("id") Long id) {
        servicioMecanicoSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
