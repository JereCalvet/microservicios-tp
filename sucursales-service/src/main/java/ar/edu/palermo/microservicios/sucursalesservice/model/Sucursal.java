package ar.edu.palermo.microservicios.sucursalesservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated
    private Pais pais;

    @NotNull
    @Enumerated
    private Provincia provincia;

    @NotBlank
    @Size(min = 3, max = 50, message = "La localidad debe tener entre 3 y 50 caracteres")
    private String localidad;

    @NotBlank
    @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
    private String direccion;

    @NotBlank
    @Size(min = 3, max = 50, message = "El nombre ficticio debe tener entre 3 y 50 caracteres")
    private String nombreFicticio;

    @PastOrPresent
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaApertura;
}
