package ar.edu.palermo.microservicios.stockservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Almacen no encontrado")
public class AlmacenNotFoundException extends RuntimeException {
    private static final String ALMACEN_ID_NOT_FOUND_ERROR_MSG = "Almacen id %d not found.";

    public AlmacenNotFoundException(Long configId) {
        super(String.format(ALMACEN_ID_NOT_FOUND_ERROR_MSG, configId));
    }
}



