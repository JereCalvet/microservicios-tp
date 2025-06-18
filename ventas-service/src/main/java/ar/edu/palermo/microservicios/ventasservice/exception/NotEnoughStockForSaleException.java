package ar.edu.palermo.microservicios.ventasservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "No hay suficiente stock disponible para realizar la venta.")
public class NotEnoughStockForSaleException extends RuntimeException {
    private static final String OUT_OF_STOCK_ERROR_MSG = "No hay suficiente stock disponible para realizar la venta.";

    public NotEnoughStockForSaleException() {
        super(OUT_OF_STOCK_ERROR_MSG);
    }
}


