package ar.edu.palermo.microservicios.ventasservice.model;

public record StockRequestDTO(
        Long idSucursal,
        Long idVehiculo,
        Integer cantidadRequerida
) { }
