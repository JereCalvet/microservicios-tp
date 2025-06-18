package ar.edu.palermo.microservicios.stockservice.model.almacen;

public record StockRequestDTO(
        Long idSucursal,
        Long idVehiculo,
        Integer cantidadRequerida
) { }
