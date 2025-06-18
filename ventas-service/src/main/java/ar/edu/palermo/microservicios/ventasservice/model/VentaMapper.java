package ar.edu.palermo.microservicios.ventasservice.model;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class VentaMapper {

    public abstract VentaResponseDTO toResponseDTO(Venta venta);
}
