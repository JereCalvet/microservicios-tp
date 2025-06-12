package ar.edu.palermo.microservicios.sucursalesservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Sucursal no encontrada")
public class SucursalNotFoundException extends RuntimeException {

    private static final String SUCURSAL_ID_NOT_FOUND_ERROR_MSG = "Sucursal id %d not found.";

    public SucursalNotFoundException(Long sucursalId) {
        super(String.format(SUCURSAL_ID_NOT_FOUND_ERROR_MSG, sucursalId));
    }
}
