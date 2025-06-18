package ar.edu.palermo.microservicios.ventasservice.model;

import java.time.LocalDate;

public record VentaResponseDTO(
        Long id,
        LocalDate fechaVenta,
        Long clienteId,
        String nombreCliente,
        String dniCliente,
        Long sucursalId,
        Long vehiculoId,
        Long vendedorId,
        Integer cantidad,
        Double precioUnitario,
        Double totalVenta,
        String observaciones
) { }

