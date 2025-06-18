package ar.edu.palermo.microservicios.mecanicaservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ServicioMecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private Boolean garantia = false;

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    private String nombreCliente;

    @NotBlank(message = "El DNI del cliente no puede estar vacío")
    private String dniCliente;

    private String telefonoCliente;
    private String emailCliente;

    @Builder.Default
    private LocalDate fechaServicio = LocalDate.now();

    @NotNull(message = "La fecha de entrega del vehículo no puede ser nula")
    private LocalDate fechaEntrega;

    @NotNull(message = "El vehículo no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private TipoVehiculo tipoVehiculo;

    @Builder.Default
    private int kilometraje = 0;
    private String descripcionProblema;

    @NotBlank(message = "La patente del vehículo no puede estar vacía")
    private String patenteVehiculo;
}
