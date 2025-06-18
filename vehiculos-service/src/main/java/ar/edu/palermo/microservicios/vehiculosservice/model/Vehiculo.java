package ar.edu.palermo.microservicios.vehiculosservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, max = 30, message = "La marca debe tener entre 6 y 20 caracteres")
    @NotBlank
    private String marca;

    @Size(min = 6, max = 30, message = "El modelo debe tener entre 6 y 20 caracteres")
    @NotBlank
    private String modelo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoVehiculo tipo;
}
