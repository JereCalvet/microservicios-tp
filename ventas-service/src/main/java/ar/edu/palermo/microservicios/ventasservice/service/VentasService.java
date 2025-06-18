package ar.edu.palermo.microservicios.ventasservice.service;

import ar.edu.palermo.microservicios.ventasservice.model.DatosFacturaDTO;
import ar.edu.palermo.microservicios.ventasservice.model.VentaResponseDTO;

import java.util.List;

public interface VentasService {
    List<VentaResponseDTO> findAll();
    VentaResponseDTO findById(Long id);
    VentaResponseDTO findByPatenteVehiculo(String patenteVehiculo);
    void realizarVenta(DatosFacturaDTO datosFacturaDTO);
}
