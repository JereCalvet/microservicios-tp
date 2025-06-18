package ar.edu.palermo.microservicios.mecanicaservice.service;

import ar.edu.palermo.microservicios.mecanicaservice.model.ServicioMecanicoCreateDTO;
import ar.edu.palermo.microservicios.mecanicaservice.model.ServicioMecanicoPatchDTO;
import ar.edu.palermo.microservicios.mecanicaservice.model.ServicioMecanicoResponseDTO;
import ar.edu.palermo.microservicios.mecanicaservice.model.ServicioMecanicoUpdateDTO;

import java.util.List;

public interface ServicioMecanicoService {
    ServicioMecanicoResponseDTO save(ServicioMecanicoCreateDTO dto);
    List<ServicioMecanicoResponseDTO> findAll();
    ServicioMecanicoResponseDTO findById(Long id);
    ServicioMecanicoResponseDTO update(Long id, ServicioMecanicoUpdateDTO dto);
    ServicioMecanicoResponseDTO update(Long id, ServicioMecanicoPatchDTO dto);
    void delete(Long id);
}
