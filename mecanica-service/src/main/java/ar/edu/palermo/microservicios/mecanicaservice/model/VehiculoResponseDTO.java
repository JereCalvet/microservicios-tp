package ar.edu.palermo.microservicios.mecanicaservice.model;

public record VehiculoResponseDTO(
        Long id,
        String marca,
        String modelo,
        TipoVehiculo tipo
) {}
