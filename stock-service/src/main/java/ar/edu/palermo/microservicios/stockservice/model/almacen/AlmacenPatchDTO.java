package ar.edu.palermo.microservicios.stockservice.model.almacen;


public record AlmacenPatchDTO(
        Long sucursalId,
        String nombre,
        TipoAlmacen tipo
) {
}
