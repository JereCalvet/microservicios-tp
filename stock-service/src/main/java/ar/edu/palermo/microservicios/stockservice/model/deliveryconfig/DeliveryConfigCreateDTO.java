package ar.edu.palermo.microservicios.stockservice.model.deliveryconfig;

import jakarta.validation.constraints.NotNull;

public record DeliveryConfigCreateDTO(
        @NotNull
        Long desdeAlmacenId,
        @NotNull
        Long hastaAlmacenId,
        @NotNull
        Integer tiempoEntregaEstimadoEnDias
) { }
