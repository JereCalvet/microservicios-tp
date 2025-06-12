package ar.edu.palermo.microservicios.vehiculosservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VehiculoCreateDTO(
        @NotBlank
        @Size(min = 6, max = 30, message = "La marca debe tener entre 6 y 30 caracteres")
        String marca,

        @NotBlank
        @Size(min = 6, max = 30, message = "El modelo debe tener entre 6 y 30 caracteres")
        String modelo,

        @NotNull
        TipoVehiculo tipo
) {
}
