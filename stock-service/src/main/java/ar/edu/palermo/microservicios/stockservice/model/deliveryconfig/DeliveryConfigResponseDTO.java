package ar.edu.palermo.microservicios.stockservice.model.deliveryconfig;

public record DeliveryConfigResponseDTO(
        Long id,
        Long desdeAlmacenId,
        Long hastaAlmacenId,
        Integer tiempoEntregaEstimadoEnDias)
{ }
