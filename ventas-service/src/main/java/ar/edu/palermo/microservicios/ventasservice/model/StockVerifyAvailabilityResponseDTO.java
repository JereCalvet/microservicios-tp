package ar.edu.palermo.microservicios.ventasservice.model;

public record StockVerifyAvailabilityResponseDTO(
    int requiredQuantity,
    boolean isAvailable,
    Long idAlmacenLocal,
    int availableLocal,
    Long idAlmacenCentral,
    int availableCentral,
    Long idVehiculo
) {
}
