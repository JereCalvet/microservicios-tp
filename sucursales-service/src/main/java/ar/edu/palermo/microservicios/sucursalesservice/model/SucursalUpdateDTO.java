package ar.edu.palermo.microservicios.sucursalesservice.model;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record SucursalUpdateDTO(
        @NotNull(message = "El país no puede ser nulo")
        Pais pais,

        @NotNull(message = "La provincia no puede ser nula")
        Provincia provincia,

        @NotBlank
        @Size(min = 3, max = 50, message = "La localidad debe tener entre 3 y 50 caracteres")
        String localidad,

        @NotBlank
        @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
        String direccion,

        @NotBlank
        @Size(min = 3, max = 50, message = "El nombre ficticio debe tener entre 3 y 50 caracteres")
        String nombreFicticio,

        @NotNull(message = "La fecha de apertura no puede ser nula")
        @PastOrPresent
        LocalDate fechaApertura
) { }
