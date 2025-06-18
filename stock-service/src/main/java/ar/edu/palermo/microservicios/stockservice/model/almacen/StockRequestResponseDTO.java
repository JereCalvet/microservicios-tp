package ar.edu.palermo.microservicios.stockservice.model.almacen;

public record StockRequestResponseDTO(
    Long almacenDestinoId,
    Integer tiempoEntregaEstimadoEnDias
) { }
