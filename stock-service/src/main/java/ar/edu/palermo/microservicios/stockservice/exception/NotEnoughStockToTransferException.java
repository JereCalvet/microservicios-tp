package ar.edu.palermo.microservicios.stockservice.exception;

public class NotEnoughStockToTransferException extends RuntimeException {
    private static final String NOT_ENOUGH_STOCK_ERROR_MSG = "No hay suficiente stock para realizar la transferencia. Stock actual: %d.";

    public NotEnoughStockToTransferException(Integer stockActual) {
        super(String.format(NOT_ENOUGH_STOCK_ERROR_MSG, stockActual));
    }
}
