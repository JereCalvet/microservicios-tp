package ar.edu.palermo.microservicios.mecanicaservice.service;

import ar.edu.palermo.microservicios.mecanicaservice.configuration.GarantiaConfig;
import ar.edu.palermo.microservicios.mecanicaservice.exception.ServicioMecanicoNotFoundException;
import ar.edu.palermo.microservicios.mecanicaservice.model.*;
import ar.edu.palermo.microservicios.mecanicaservice.repository.ServicioMecanicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ServicioMecanicoServiceImpl implements ServicioMecanicoService {

    private final ServicioMecanicoRepository servicioMecanicoRepository;
    private final ServicioMecanicoMapper servicioMecanicoMapper;
    private final GarantiaConfig garantiaConfig;

    @Override
    public ServicioMecanicoResponseDTO save(ServicioMecanicoCreateDTO dto) {
        ServicioMecanico entity = servicioMecanicoMapper.toEntity(dto);
        entity.setFechaServicio(LocalDate.now());

        //todo: calcular si esta en garantía
        ServicioMecanico saved = servicioMecanicoRepository.save(entity);
        return servicioMecanicoMapper.toResponseDTO(saved);
    }

    @Override
    public List<ServicioMecanicoResponseDTO> findAll() {
        List<ServicioMecanico> servicios = servicioMecanicoRepository.findAll();
        return servicios.stream()
                .map(servicioMecanicoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ServicioMecanicoResponseDTO findById(Long id) {
        ServicioMecanico servicioMecanicoFound = servicioMecanicoRepository.findById(id)
                .orElseThrow(() -> new ServicioMecanicoNotFoundException(id));
        return servicioMecanicoMapper.toResponseDTO(servicioMecanicoFound);
    }

    @Override
    public ServicioMecanicoResponseDTO update(Long id, ServicioMecanicoUpdateDTO dto) {
        ServicioMecanico servicioMecanicoToUpdate = servicioMecanicoRepository.findById(id)
                .orElseThrow(() -> new ServicioMecanicoNotFoundException(id));
        servicioMecanicoMapper.toEntity(dto, servicioMecanicoToUpdate);
        ServicioMecanico updated = servicioMecanicoRepository.save(servicioMecanicoToUpdate);
        return servicioMecanicoMapper.toResponseDTO(updated);
    }

    @Override
    public ServicioMecanicoResponseDTO update(Long id, ServicioMecanicoPatchDTO dto) {
        ServicioMecanico servicioMecanicoToUpdate = servicioMecanicoRepository.findById(id)
                .orElseThrow(() -> new ServicioMecanicoNotFoundException(id));
        servicioMecanicoMapper.toEntity(dto, servicioMecanicoToUpdate);
        ServicioMecanico updated = servicioMecanicoRepository.save(servicioMecanicoToUpdate);
        return servicioMecanicoMapper.toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        ServicioMecanico servicioMecanicoToDelete = servicioMecanicoRepository.findById(id)
                .orElseThrow(() -> new ServicioMecanicoNotFoundException(id));
        servicioMecanicoRepository.delete(servicioMecanicoToDelete);
    }
}
