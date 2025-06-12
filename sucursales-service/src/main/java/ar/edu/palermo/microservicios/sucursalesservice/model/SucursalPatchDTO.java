package ar.edu.palermo.microservicios.sucursalesservice.model;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record SucursalPatchDTO(
        Pais pais,

        Provincia provincia,

        @Size(min = 3, max = 50, message = "La localidad debe tener entre 3 y 50 caracteres")
        String localidad,

        @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
        String direccion,

        @Size(min = 3, max = 50, message = "El nombre ficticio debe tener entre 3 y 50 caracteres")
        String nombreFicticio,

        @PastOrPresent
        LocalDate fechaApertura
) { }
