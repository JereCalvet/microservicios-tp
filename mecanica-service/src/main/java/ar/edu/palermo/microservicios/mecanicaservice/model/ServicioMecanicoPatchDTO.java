package ar.edu.palermo.microservicios.mecanicaservice.model;

import java.time.LocalDate;

public record ServicioMecanicoPatchDTO(
        String nombreCliente,

        String dniCliente,

        String telefonoCliente,
        String emailCliente,
        LocalDate fechaEntrega,

        TipoVehiculo tipoVehiculo,

        int kilometraje,

        String descripcionProblema,

        String patenteVehiculo
) { }
