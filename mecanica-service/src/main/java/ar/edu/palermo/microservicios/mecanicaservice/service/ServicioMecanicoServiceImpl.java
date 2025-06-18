package ar.edu.palermo.microservicios.mecanicaservice.service;

import ar.edu.palermo.microservicios.mecanicaservice.configuration.GarantiaConfig;
import ar.edu.palermo.microservicios.mecanicaservice.exception.ServicioMecanicoNotFoundException;
import ar.edu.palermo.microservicios.mecanicaservice.integration.VehiculoClient;
import ar.edu.palermo.microservicios.mecanicaservice.integration.VentasClient;
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
    private final VentasClient ventasClient;
    private final VehiculoClient vehiculoClient;
    private final GarantiaConfig garantiaConfig;

    @Override
    public ServicioMecanicoResponseDTO save(ServicioMecanicoCreateDTO dto) {
        ServicioMecanico entity = servicioMecanicoMapper.toEntity(dto);
        entity.setFechaServicio(LocalDate.now());
        entity.setGarantia(estaEnGarantia(dto));
        ServicioMecanico saved = servicioMecanicoRepository.save(entity);
        return servicioMecanicoMapper.toResponseDTO(saved);
    }

    private boolean estaEnGarantia(ServicioMecanicoCreateDTO dto) {
        VentaResponseDTO ventaByPatente = null;
        try {
            ventaByPatente = ventasClient.getVentaByPatente(dto.patenteVehiculo());
        } catch (Exception e) {
            System.out.println("Error al consultar la venta por patente: " + e.getMessage());
        }
        if (ventaByPatente == null) {
            return false;
        }
        LocalDate fechaCompra = ventaByPatente.fechaVenta();
        VehiculoResponseDTO vehiculoById = null;
        try {
            vehiculoById = vehiculoClient.getVehiculoById(ventaByPatente.vehiculoId());
        } catch (Exception e) {
            System.out.println("Error al consultar el vehículo por ID: " + e.getMessage());
        }
        if (vehiculoById == null) {
            return false;
        }
        Integer aniosEnGarantia = garantiaConfig.getPorTipo(vehiculoById.tipo());
        if (aniosEnGarantia == null) {
            return false;
        }

        LocalDate fechaFinGarantia = fechaCompra.plusYears(aniosEnGarantia);
        return !LocalDate.now().isAfter(fechaFinGarantia);
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
