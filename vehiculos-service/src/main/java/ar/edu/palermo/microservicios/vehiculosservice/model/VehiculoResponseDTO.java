package ar.edu.palermo.microservicios.vehiculosservice.model;

public record VehiculoResponseDTO(
        Long id,
        String marca,
        String modelo,
        TipoVehiculo tipo
) {}
