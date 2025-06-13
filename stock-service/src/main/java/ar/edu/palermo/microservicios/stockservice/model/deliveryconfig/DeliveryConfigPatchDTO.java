package ar.edu.palermo.microservicios.stockservice.model.deliveryconfig;

public record DeliveryConfigPatchDTO(
        Long desdeAlmacenId,
        Long hastaAlmacenId,
        Integer tiempoEntregaEstimadoEnDias
) { }
