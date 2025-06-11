package ar.edu.palermo.microservicios.vehiculosservice.service;

import ar.edu.palermo.microservicios.vehiculosservice.exception.VehiculoNotFoundException;
import ar.edu.palermo.microservicios.vehiculosservice.model.*;
import ar.edu.palermo.microservicios.vehiculosservice.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final VehiculoMapper vehiculoMapper;

    public VehiculoResponseDTO save(VehiculoCreateDTO dto) {
        Vehiculo entity = vehiculoMapper.toEntity(dto);
        Vehiculo saved = vehiculoRepository.save(entity);
        return vehiculoMapper.toResponseDTO(saved);
    }

    public List<VehiculoResponseDTO> findAll() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        return vehiculos.stream()
                .map(vehiculoMapper::toResponseDTO)
                .toList();
    }

    public VehiculoResponseDTO findById(Long id) {
        Vehiculo vehiculoFound = vehiculoRepository.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException(id));
        return vehiculoMapper.toResponseDTO(vehiculoFound);
    }

    public VehiculoResponseDTO update(Long id, VehiculoUpdateDTO dto) {
        Vehiculo vehiculoToUpdate = vehiculoRepository.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException(id));
        vehiculoMapper.toEntity(dto, vehiculoToUpdate);
        Vehiculo updated = vehiculoRepository.save(vehiculoToUpdate);
        return vehiculoMapper.toResponseDTO(updated);
    }

    public VehiculoResponseDTO update(Long id, VehiculoPatchDTO dto) {
        Vehiculo vehiculoToUpdate = vehiculoRepository.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException(id));
        vehiculoMapper.toEntity(dto, vehiculoToUpdate);
        Vehiculo updated = vehiculoRepository.save(vehiculoToUpdate);
        return vehiculoMapper.toResponseDTO(updated);
    }

    public void delete(Long id) {
        Vehiculo vehiculoToDelete = vehiculoRepository.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException(id));
        vehiculoRepository.delete(vehiculoToDelete);
    }
}
