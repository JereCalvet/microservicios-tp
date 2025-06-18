package ar.edu.palermo.microservicios.stockservice.controller;

import ar.edu.palermo.microservicios.stockservice.model.almacen.*;
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

    /**
     * Confirma la solicitud de stock para un vehículo en una sucursal específica.
     * Si no hay suficiente stock en la sucursal, se intentará obtener del almacén central.
     * Si no hay stock suficiente en ninguna parte, se lanzará una excepción.
     * Posteriormente, si corresponde, realiza la transferencia de stock y se actualiza el stock de la sucursal y el almacén central.
     */
    @PostMapping("/stock-request")
    public ResponseEntity<StockRequestResponseDTO> stockRequest(
            @RequestBody @Valid StockRequestDTO dto
    ) {

        return ResponseEntity.ok(
                almacenSvc.stockRequest(
                        dto.idSucursal(),
                        dto.idVehiculo(),
                        dto.cantidadRequerida()
                )
        );
    }

    /**
     * Verifica la disponibilidad de stock en una sucursal específica y en el almacén central.
     */
    @GetMapping("/verify-availability/{idSucursal}/vehiculo/{idVehiculo}")
    public ResponseEntity<StockVerifyAvailabilityResponseDTO> verifyAvailability(
            @PathVariable("idSucursal") Long idSucursal,
            @PathVariable("idVehiculo") Long idVehiculo,
            @RequestParam(value = "cantidad", defaultValue = "1") Integer cantidad) {

        return ResponseEntity.ok(almacenSvc.verifyAvailability(idSucursal, idVehiculo, cantidad));
    }

    /**
     * Helper - Verifica la disponibilidad de stock en una sucursal específica.
     */
    @GetMapping("/sucursal/check-stock/{idSucursal}/vehiculo/{idVehiculo}")
    public ResponseEntity<StockStatusResponseDTO> checkStockSucursal(
            @PathVariable("idSucursal") Long idSucursal,
            @PathVariable("idVehiculo") Long idVehiculo) {

        return ResponseEntity.ok(almacenSvc.checkStockSucursal(idSucursal, idVehiculo));
    }

    /**
     * Helper - Verifica la disponibilidad de stock en un almacén específico.
     */
    @GetMapping("/check-stock/{idAlmacen}/vehiculo/{idVehiculo}")
    public ResponseEntity<StockStatusResponseDTO> checkStockAlmacen(
            @PathVariable("idAlmacen") Long idAlmacen,
            @PathVariable("idVehiculo") Long idVehiculo) {

        return ResponseEntity.ok(almacenSvc.checkStockAlmacen(idAlmacen, idVehiculo));
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
