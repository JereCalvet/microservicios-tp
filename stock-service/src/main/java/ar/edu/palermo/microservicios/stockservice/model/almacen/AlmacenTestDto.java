package ar.edu.palermo.microservicios.stockservice.model.almacen;

import jakarta.validation.constraints.NotNull;

public record AlmacenTestDto(
        @NotNull(message = "idSucursal no puede ser nulo")
        Long idSucursal,
        @NotNull(message = "idVehiculo no puede ser nulo")
        Long idVehiculo,
        @NotNull(message = "cantidad no puede ser nulo")
        Integer cantidad
) {
}
