package ar.edu.palermo.microservicios.ventasservice.exception;


public class NotEnoughStockForSaleException extends RuntimeException {
    private static final String OUT_OF_STOCK_ERROR_MSG = "No hay suficiente stock disponible para realizar la venta.";

    public NotEnoughStockForSaleException() {
        super(OUT_OF_STOCK_ERROR_MSG);
    }
}


