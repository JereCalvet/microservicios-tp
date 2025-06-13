package ar.edu.palermo.microservicios.sucursalesservice.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class SucursalMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Sucursal toEntity(SucursalCreateDTO dto);
    @Mapping(target = "id", ignore = true)
    public abstract Sucursal toEntity(SucursalUpdateDTO dto, @MappingTarget Sucursal sucursal);
    @Mapping(target = "id", ignore = true)
    public abstract Sucursal toEntity(SucursalPatchDTO dto, @MappingTarget Sucursal sucursal);

    public abstract SucursalResponseDTO toResponseDTO(Sucursal sucursal);
}
