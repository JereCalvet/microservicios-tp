package ar.edu.palermo.microservicios.vehiculosservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VehiculoUpdateDTO(
        @NotBlank
        @Size(min = 6, max = 30, message = "La marca debe tener entre 6 y 30 caracteres")
        String marca,

        @NotBlank
        @Size(min = 6, max = 30, message = "El modelo debe tener entre 6 y 30 caracteres")
        String modelo,

        @NotBlank
        TipoVehiculo tipo
) {
}
