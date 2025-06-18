package ar.edu.palermo.microservicios.mecanicaservice.model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ServicioMecanicoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaServicio", ignore = true)
    public abstract ServicioMecanico toEntity(ServicioMecanicoCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaServicio", ignore = true)
    @Mapping(target = "garantia", ignore = true)
    public abstract ServicioMecanico toEntity(ServicioMecanicoUpdateDTO dto, @MappingTarget ServicioMecanico servicioMecanico);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaServicio", ignore = true)
    @Mapping(target = "garantia", ignore = true)
    public abstract ServicioMecanico toEntity(ServicioMecanicoPatchDTO dto, @MappingTarget ServicioMecanico servicioMecanico);

    public abstract ServicioMecanicoResponseDTO toResponseDTO(ServicioMecanico servicioMecanico);
}
