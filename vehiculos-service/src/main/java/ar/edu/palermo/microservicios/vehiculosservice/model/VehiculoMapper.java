package ar.edu.palermo.microservicios.vehiculosservice.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class VehiculoMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Vehiculo toEntity(VehiculoCreateDTO dto);
    @Mapping(target = "id", ignore = true)
    public abstract Vehiculo toEntity(VehiculoUpdateDTO dto, @MappingTarget Vehiculo vehiculo);
    @Mapping(target = "id", ignore = true)
    public abstract Vehiculo toEntity(VehiculoPatchDTO dto, @MappingTarget Vehiculo vehiculo);

    public abstract VehiculoResponseDTO toResponseDTO(Vehiculo vehiculo);
}
