package ar.edu.palermo.microservicios.stockservice.model.almacen;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class AlmacenMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stock", ignore = true)
    public abstract Almacen toEntity(AlmacenCreateDTO dto);

    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Almacen toEntity(AlmacenUpdateDTO dto, @MappingTarget Almacen almacen);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stock", ignore = true)
    public abstract Almacen toEntity(AlmacenPatchDTO dto, @MappingTarget Almacen almacen);

    public abstract AlmacenResponseDTO toResponseDTO(Almacen almacen);
}
