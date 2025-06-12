package ar.edu.palermo.microservicios.sucursalesservice.service;

import ar.edu.palermo.microservicios.sucursalesservice.model.SucursalCreateDTO;
import ar.edu.palermo.microservicios.sucursalesservice.model.SucursalPatchDTO;
import ar.edu.palermo.microservicios.sucursalesservice.model.SucursalResponseDTO;
import ar.edu.palermo.microservicios.sucursalesservice.model.SucursalUpdateDTO;

import java.util.List;

public interface SucursalService {
    SucursalResponseDTO save(SucursalCreateDTO dto);
    List<SucursalResponseDTO> findAll();
    SucursalResponseDTO findById(Long id);
    SucursalResponseDTO update(Long id, SucursalUpdateDTO dto);
    SucursalResponseDTO update(Long id, SucursalPatchDTO dto);
    void delete(Long id);
}

