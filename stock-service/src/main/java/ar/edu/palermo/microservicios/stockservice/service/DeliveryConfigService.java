package ar.edu.palermo.microservicios.stockservice.service;

import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfigCreateDTO;
import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfigPatchDTO;
import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfigResponseDTO;
import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfigUpdateDTO;

import java.util.List;

public interface DeliveryConfigService {
    DeliveryConfigResponseDTO save(DeliveryConfigCreateDTO dto);
    List<DeliveryConfigResponseDTO> findAll();
    DeliveryConfigResponseDTO findById(Long id);
    DeliveryConfigResponseDTO fromAlmacenIdToAlmacenId(Long from, Long to);
    DeliveryConfigResponseDTO update(Long id, DeliveryConfigUpdateDTO dto);
    DeliveryConfigResponseDTO update(Long id, DeliveryConfigPatchDTO dto);
    void delete(Long id);
}
