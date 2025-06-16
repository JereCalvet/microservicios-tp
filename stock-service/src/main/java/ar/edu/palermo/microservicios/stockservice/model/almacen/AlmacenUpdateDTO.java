package ar.edu.palermo.microservicios.stockservice.model.almacen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AlmacenUpdateDTO(
        @NotNull
        Long sucursalId,

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre,

        @NotNull
        TipoAlmacen tipo
) {
}
