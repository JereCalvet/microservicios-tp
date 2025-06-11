package ar.edu.palermo.microservicios.vehiculosservice.service;

import ar.edu.palermo.microservicios.vehiculosservice.model.VehiculoCreateDTO;
import ar.edu.palermo.microservicios.vehiculosservice.model.VehiculoPatchDTO;
import ar.edu.palermo.microservicios.vehiculosservice.model.VehiculoResponseDTO;
import ar.edu.palermo.microservicios.vehiculosservice.model.VehiculoUpdateDTO;

import java.util.List;

public interface VehiculoService {
    VehiculoResponseDTO save(VehiculoCreateDTO dto);
    List<VehiculoResponseDTO> findAll();
    VehiculoResponseDTO findById(Long id);
    VehiculoResponseDTO update(Long id, VehiculoUpdateDTO dto);
    VehiculoResponseDTO update(Long id, VehiculoPatchDTO dto);
    void delete(Long id);
}
