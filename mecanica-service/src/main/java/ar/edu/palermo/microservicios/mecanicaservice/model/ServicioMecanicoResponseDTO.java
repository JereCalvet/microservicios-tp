package ar.edu.palermo.microservicios.mecanicaservice.model;

import java.time.LocalDate;

public record ServicioMecanicoResponseDTO(
        Long id,
        Boolean garantia,
        String nombreCliente,
        String dniCliente,
        String telefonoCliente,
        String emailCliente,
        LocalDate fechaServicio,
        LocalDate fechaEntrega,
        TipoVehiculo tipoVehiculo,
        int kilometraje,
        String descripcionProblema,
        String patenteVehiculo
) {
}
