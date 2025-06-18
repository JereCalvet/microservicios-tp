package ar.edu.palermo.microservicios.ventasservice.model;

public record StockRequestResponseDTO(
        Long almacenDestinoId,
        Integer tiempoEntregaEstimadoEnDias
) { }