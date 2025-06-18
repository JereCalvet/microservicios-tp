package ar.edu.palermo.microservicios.ventasservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Venta no encontrada")
public class VentaNotFoundException extends RuntimeException {

    private static final String VENTA_ID_NOT_FOUND_ERROR_MSG = "Venta id %d not found.";

    public VentaNotFoundException(Long idVenta) {
        super(String.format(VENTA_ID_NOT_FOUND_ERROR_MSG, idVenta));
    }
}



