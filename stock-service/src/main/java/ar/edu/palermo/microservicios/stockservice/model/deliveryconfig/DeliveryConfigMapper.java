package ar.edu.palermo.microservicios.stockservice.model.deliveryconfig;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class DeliveryConfigMapper {

    @Mapping(target = "id", ignore = true)
    public abstract DeliveryConfig toEntity(DeliveryConfigCreateDTO dto);
    @Mapping(target = "id", ignore = true)
    public abstract DeliveryConfig toEntity(DeliveryConfigUpdateDTO dto, @MappingTarget DeliveryConfig deliveryConfig);
    @Mapping(target = "id", ignore = true)
    public abstract DeliveryConfig toEntity(DeliveryConfigPatchDTO dto, @MappingTarget DeliveryConfig deliveryConfig);

    public abstract DeliveryConfigResponseDTO toResponseDTO(DeliveryConfig deliveryConfig);
}
