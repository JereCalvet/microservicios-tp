package ar.edu.palermo.microservicios.stockservice.service;

import ar.edu.palermo.microservicios.stockservice.model.almacen.*;

import java.util.List;

public interface AlmacenService {
    AlmacenResponseDTO save(AlmacenCreateDTO dto);
    List<AlmacenResponseDTO> findAll();
    AlmacenResponseDTO findById(Long id);
    AlmacenResponseDTO update(Long id, AlmacenUpdateDTO dto);
    AlmacenResponseDTO update(Long id, AlmacenPatchDTO dto);
    void delete(Long id);

    StockStatusResponseDTO checkStock(Long idAlmacen, Long idVehiculo);
    StockStatusResponseDTO addStock(Long idAlmacen, Long idVehiculo, int cantidad);
    StockStatusResponseDTO removeStock(Long idAlmacen, Long idVehiculo, Integer cantidad);
}
