package ar.edu.palermo.microservicios.ventasservice.model;

public record StockStatusResponseDTO(
        Long idAlmacen,
        Long idVehiculo,
        Integer cantidadVehiculos
) { }
