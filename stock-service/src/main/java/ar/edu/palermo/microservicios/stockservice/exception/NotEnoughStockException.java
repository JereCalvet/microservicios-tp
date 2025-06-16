package ar.edu.palermo.microservicios.stockservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "No hay suficiente stock")
public class NotEnoughStockException extends RuntimeException {
    private static final String NOT_ENOUGH_STOCK_ERROR_MSG = "No hay suficiente stock. Stock actual: %d.";

    public NotEnoughStockException(Integer stockActual) {
        super(String.format(NOT_ENOUGH_STOCK_ERROR_MSG, stockActual));
    }
}
