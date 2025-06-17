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
    public DeliveryConfigResponseDTO fromAlmacenIdToAlmacenId(Long from, Long to) {
        DeliveryConfig deliveryConfigFound = deliveryConfigRepository.findByDesdeAlmacenIdAndHastaAlmacenId(from, to)
                .orElseThrow(() -> new DeliveryConfigNotFoundException(from, to));
        return deliveryConfigMapper.toResponseDTO(deliveryConfigFound);
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
