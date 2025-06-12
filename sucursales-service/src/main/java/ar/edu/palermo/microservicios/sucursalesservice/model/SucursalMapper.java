package ar.edu.palermo.microservicios.sucursalesservice.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class SucursalMapper {

    public abstract Sucursal toEntity(SucursalCreateDTO dto);
    public abstract Sucursal toEntity(SucursalUpdateDTO dto, @MappingTarget Sucursal sucursal);
    public abstract Sucursal toEntity(SucursalPatchDTO dto, @MappingTarget Sucursal sucursal);

    public abstract SucursalResponseDTO toResponseDTO(Sucursal sucursal);
}
