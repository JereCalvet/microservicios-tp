package ar.edu.palermo.microservicios.stockservice.service;

import ar.edu.palermo.microservicios.stockservice.exception.DeliveryConfigNotFoundException;
import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.*;
import ar.edu.palermo.microservicios.stockservice.repository.DeliveryConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryConfigServiceImpl implements DeliveryConfigService {

    private final DeliveryConfigRepository deliveryConfigRepository;
    private final DeliveryConfigMapper deliveryConfigMapper;

    @Override
    public DeliveryConfigResponseDTO save(DeliveryConfigCreateDTO dto) {
        DeliveryConfig entity = deliveryConfigMapper.toEntity(dto);
        DeliveryConfig saved = deliveryConfigRepository.save(entity);
        return deliveryConfigMapper.toResponseDTO(saved);
    }

    @Override
    public List<DeliveryConfigResponseDTO> findAll() {
        List<DeliveryConfig> deliveryConfigs = deliveryConfigRepository.findAll();
        return deliveryConfigs.stream()
                .map(deliveryConfigMapper::toResponseDTO)
                .toList();
    }

    @Override
    public DeliveryConfigResponseDTO findById(Long id) {
        DeliveryConfig deliveryConfigFound = deliveryConfigRepository.findById(id)
                .orElseThrow(() -> new DeliveryConfigNotFoundException(id));
        return deliveryConfigMapper.toResponseDTO(deliveryConfigFound);
    }

    @Override
    public DeliveryConfigResponseDTO toAlmacenId(Long id) {
        //TODO: Implementar este método
        //obtener del servicio de almacenes el id del almacén central
        //y luego buscar la configuración de entrega desde el almacén central hacia el almacén con el id proporcionado
        return null;
    }

    @Override
    public DeliveryConfigResponseDTO update(Long id, DeliveryConfigUpdateDTO dto) {
        DeliveryConfig deliveryConfigToUpdate = deliveryConfigRepository.findById(id)
                .orElseThrow(() -> new DeliveryConfigNotFoundException(id));
        deliveryConfigMapper.toEntity(dto, deliveryConfigToUpdate);
        DeliveryConfig updated = deliveryConfigRepository.save(deliveryConfigToUpdate);
        return deliveryConfigMapper.toResponseDTO(updated);
    }

    @Override
    public DeliveryConfigResponseDTO update(Long id, DeliveryConfigPatchDTO dto) {
        DeliveryConfig deliveryConfigToUpdate = deliveryConfigRepository.findById(id)
                .orElseThrow(() -> new DeliveryConfigNotFoundException(id));
        deliveryConfigMapper.toEntity(dto, deliveryConfigToUpdate);
        DeliveryConfig updated = deliveryConfigRepository.save(deliveryConfigToUpdate);
        return deliveryConfigMapper.toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        DeliveryConfig deliveryConfigToDelete = deliveryConfigRepository.findById(id)
                .orElseThrow(() -> new DeliveryConfigNotFoundException(id));
        deliveryConfigRepository.delete(deliveryConfigToDelete);
    }
}
