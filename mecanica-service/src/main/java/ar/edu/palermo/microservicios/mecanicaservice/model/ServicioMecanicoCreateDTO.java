package ar.edu.palermo.microservicios.mecanicaservice.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ServicioMecanicoCreateDTO(
        @NotBlank(message = "El DNI del cliente no puede estar vacío")
        String nombreCliente,

        @NotBlank(message = "El DNI del cliente no puede estar vacío")
        String dniCliente,

        String telefonoCliente,
        String emailCliente,

        @Future(message = "La fecha de entrega del vehículo debe ser una fecha futura")
        LocalDate fechaEntrega,

        @NotNull(message = "El tipo de vehículo no puede ser nulo")
        TipoVehiculo tipoVehiculo,

        int kilometraje,

        String descripcionProblema,

        @NotBlank(message = "La patente del vehículo no puede estar vacía")
        String patenteVehiculo
) { }

