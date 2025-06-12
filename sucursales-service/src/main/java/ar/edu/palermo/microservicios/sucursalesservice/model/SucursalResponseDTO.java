package ar.edu.palermo.microservicios.sucursalesservice.model;

public record SucursalResponseDTO(
        Long id,
        String pais,
        String provincia,
        String localidad,
        String direccion,
        String nombreFicticio,
        String fechaApertura
) {}
