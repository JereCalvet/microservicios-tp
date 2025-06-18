package ar.edu.palermo.microservicios.ventasservice.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DatosFacturaDTO(
        @NotNull(message = "El ID del vendedor no puede ser nulo")
        Long vehiculoId,

        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        Integer cantidad,

        @NotNull(message = "El precio unitario no puede ser nulo")
        Double precioUnitario,

        @NotNull(message = "Concepto de venta no puede ser nulo")
        String descripcion,

        @NotNull(message = "Nombre del cliente no puede ser nulo")
        @Size(min = 3, message = "El nombre del cliente debe mínimo tener 3 caracteres")
        String nombreCliente,

        @NotNull(message = "DNI del cliente no puede ser nulo")
        @Size(min = 3, message = "El DNI del cliente debe mínimo tener 3 caracteres")
        String dniCliente
) { }

