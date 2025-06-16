package ar.edu.palermo.microservicios.stockservice.model.almacen;

public record AlmacenResponseDTO(
        Long id,
        Long sucursalId,
        String nombre,
        TipoAlmacen tipo
) {
}
