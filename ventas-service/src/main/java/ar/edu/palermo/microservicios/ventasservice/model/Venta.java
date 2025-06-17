package ar.edu.palermo.microservicios.ventasservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Venta {

    @Id
    private Long id;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long sucursalId;

    @NotNull
    private Long vehiculoId;

    @NotNull
    private Long vendedorId;

    @NotNull
    private Integer cantidad = 1;

    @NotNull
    private LocalDate fechaVenta = LocalDate.now();

    @NotNull
    private Double precioUnitario = 0.0;

    @NotNull
    private Double totalVenta = 0.0;
}
