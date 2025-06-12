package ar.edu.palermo.microservicios.sucursalesservice.service;

import ar.edu.palermo.microservicios.sucursalesservice.exception.SucursalNotFoundException;
import ar.edu.palermo.microservicios.sucursalesservice.model.*;
import ar.edu.palermo.microservicios.sucursalesservice.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;

    @Override
    public SucursalResponseDTO save(SucursalCreateDTO dto) {
        Sucursal entity = sucursalMapper.toEntity(dto);
        Sucursal saved = sucursalRepository.save(entity);
        return sucursalMapper.toResponseDTO(saved);
    }

    @Override
    public List<SucursalResponseDTO> findAll() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        return sucursales.stream()
                .map(sucursalMapper::toResponseDTO)
                .toList();
    }

    @Override
    public SucursalResponseDTO findById(Long id) {
        Sucursal sucursalFound = sucursalRepository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException(id));
        return sucursalMapper.toResponseDTO(sucursalFound);
    }

    @Override
    public SucursalResponseDTO update(Long id, SucursalUpdateDTO dto) {
        Sucursal sucursalToUpdate = sucursalRepository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException(id));
        sucursalMapper.toEntity(dto, sucursalToUpdate);
        Sucursal updated = sucursalRepository.save(sucursalToUpdate);
        return sucursalMapper.toResponseDTO(updated);
    }

    @Override
    public SucursalResponseDTO update(Long id, SucursalPatchDTO dto) {
        Sucursal sucursalToUpdate = sucursalRepository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException(id));
        sucursalMapper.toEntity(dto, sucursalToUpdate);
        Sucursal updated = sucursalRepository.save(sucursalToUpdate);
        return sucursalMapper.toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Sucursal sucursalToDelete = sucursalRepository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException(id));
        sucursalRepository.delete(sucursalToDelete);
    }
}
