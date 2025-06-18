package ar.edu.palermo.microservicios.ventasservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Venta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long clienteId;

    @NotBlank
    private String nombreCliente;

    @NotBlank
    private String dniCliente;

    @NotNull
    private Long sucursalId;

    @NotNull
    private Long vehiculoId;

    @NotNull
    private Long vendedorId;

    @Builder.Default
    @NotNull
    private Integer cantidad = 1;

    @Builder.Default
    @NotNull
    private LocalDate fechaVenta = LocalDate.now();

    @Builder.Default
    @NotNull
    private Double precioUnitario = 0.0;

    @Builder.Default
    @NotNull
    private Double totalVenta = 0.0;

    private String observaciones;
}
