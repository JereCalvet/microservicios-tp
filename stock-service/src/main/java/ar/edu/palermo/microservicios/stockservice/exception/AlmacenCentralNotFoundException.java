package ar.edu.palermo.microservicios.stockservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Almacén central no encontrado")
public class AlmacenCentralNotFoundException extends RuntimeException {
    private static final String ALMACEN_CENTRAL_NOT_FOUND_ERROR_MSG = "Almacén central no encontrado. Debe existir un almacén central para poder realizar la operación.";

    public AlmacenCentralNotFoundException() {
        super(ALMACEN_CENTRAL_NOT_FOUND_ERROR_MSG);
    }
}
