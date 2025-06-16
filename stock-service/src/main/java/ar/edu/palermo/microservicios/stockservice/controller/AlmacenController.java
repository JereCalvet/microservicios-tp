package ar.edu.palermo.microservicios.stockservice.controller;

import ar.edu.palermo.microservicios.stockservice.model.almacen.*;
import ar.edu.palermo.microservicios.stockservice.model.almacen.AlmacenResponseDTO;
import ar.edu.palermo.microservicios.stockservice.service.AlmacenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/almacenes")
public class AlmacenController {

    private final AlmacenService almacenSvc;

    @GetMapping("/check-stock/{idAlmacen}/vehiculo/{idVehiculo}")
    public ResponseEntity<StockStatusResponseDTO> checkStock(
            @PathVariable("idAlmacen") Long idAlmacen,
            @PathVariable("idVehiculo") Long idVehiculo) {

        return ResponseEntity.ok(almacenSvc.checkStock(idAlmacen, idVehiculo));
    }

    @PostMapping
    @RequestMapping("/add-stock/{idAlmacen}/vehiculo/{idVehiculo}")
    public ResponseEntity<StockStatusResponseDTO> addStock(
            @PathVariable("idAlmacen") Long idAlmacen,
            @PathVariable("idVehiculo") Long idVehiculo,
            @RequestParam("cantidad") int cantidad) {

        return ResponseEntity.ok(almacenSvc.addStock(idAlmacen, idVehiculo, cantidad));
    }

    @PostMapping("/remove-stock/{idAlmacen}/vehiculo/{idVehiculo}")
    public ResponseEntity<StockStatusResponseDTO> removeStock(
            @PathVariable("idAlmacen") Long idAlmacen,
            @PathVariable("idVehiculo") Long idVehiculo,
            @RequestParam("cantidad") Integer cantidad) {

        return ResponseEntity.ok(almacenSvc.removeStock(idAlmacen, idVehiculo, cantidad));
    }

    @PostMapping
    public ResponseEntity<AlmacenResponseDTO> createAlmacen(@RequestBody @Valid AlmacenCreateDTO createDto) {
        AlmacenResponseDTO savedEntity = almacenSvc.save(createDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.id())
                .toUri();

        return ResponseEntity.created(location).body(savedEntity);
    }

    @GetMapping()
    public ResponseEntity<List<AlmacenResponseDTO>> getAlmacenes() {
        return ResponseEntity.ok(almacenSvc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlmacenResponseDTO> getAlmacenById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(almacenSvc.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlmacenResponseDTO> updateAlmacenConfig(@PathVariable("id") Long id, @RequestBody @Valid AlmacenUpdateDTO updateDto) {
        return ResponseEntity.ok(almacenSvc.update(id, updateDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlmacenResponseDTO> patchAlmacenConfig(@PathVariable("id") Long id, @RequestBody @Valid AlmacenPatchDTO patchDto) {
        return ResponseEntity.ok(almacenSvc.update(id, patchDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlmacenConfig(@PathVariable("id") Long id) {
        almacenSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
