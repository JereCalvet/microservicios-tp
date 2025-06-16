package ar.edu.palermo.microservicios.stockservice.model.almacen;

public record StockStatusResponseDTO(
        Long idAlmacen,
        Long idVehiculo,
        Integer cantidadVehiculos
) { }
