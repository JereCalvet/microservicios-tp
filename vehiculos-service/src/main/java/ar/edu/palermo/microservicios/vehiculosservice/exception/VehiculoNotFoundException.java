package ar.edu.palermo.microservicios.vehiculosservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Vehículo no encontrado")
public class VehiculoNotFoundException extends RuntimeException {

    private static final String VEHICULO_ID_NOT_FOUND_ERROR_MSG = "Vehiculo id %d not found.";

    public VehiculoNotFoundException(Long vehiculoId) {
        super(String.format(VEHICULO_ID_NOT_FOUND_ERROR_MSG, vehiculoId));
    }
}
