package ar.edu.palermo.microservicios.stockservice.controller;

import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfigCreateDTO;
import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfigPatchDTO;
import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfigResponseDTO;
import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfigUpdateDTO;
import ar.edu.palermo.microservicios.stockservice.service.DeliveryConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/delivery-configs")
public class DeliveryConfigController {

    private final DeliveryConfigService deliveryConfigSvc;

    @PostMapping
    public ResponseEntity<DeliveryConfigResponseDTO> createDeliveryConfig(@RequestBody @Valid DeliveryConfigCreateDTO createDto) {
        DeliveryConfigResponseDTO savedEntity = deliveryConfigSvc.save(createDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.id())
                .toUri();

        return ResponseEntity.created(location).body(savedEntity);
    }

    @GetMapping()
    public ResponseEntity<List<DeliveryConfigResponseDTO>> getDeliveryConfigs() {
        return ResponseEntity.ok(deliveryConfigSvc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryConfigResponseDTO> getDeliveryConfigById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(deliveryConfigSvc.findById(id));
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<DeliveryConfigResponseDTO> getDeliveryConfigFromTo(
            @PathVariable("from") Long fromAlmacenId,
            @PathVariable("to") Long toAlmacenId) {
        return ResponseEntity.ok(deliveryConfigSvc.fromAlmacenIdToAlmacenId(fromAlmacenId, toAlmacenId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<DeliveryConfigResponseDTO> updateDeliveryConfig(@PathVariable("id") Long id, @RequestBody @Valid DeliveryConfigUpdateDTO updateDto) {
        return ResponseEntity.ok(deliveryConfigSvc.update(id, updateDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeliveryConfigResponseDTO> patchDeliveryConfig(@PathVariable("id") Long id, @RequestBody @Valid DeliveryConfigPatchDTO patchDto) {
        return ResponseEntity.ok(deliveryConfigSvc.update(id, patchDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryConfig(@PathVariable("id") Long id) {
        deliveryConfigSvc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
