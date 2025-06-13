package ar.edu.palermo.microservicios.stockservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "DeliveryConfig no encontrada")
public class DeliveryConfigNotFoundException extends RuntimeException {

    private static final String DELIVERY_CONFIG_ID_NOT_FOUND_ERROR_MSG = "DeliveryConfig id %d not found.";

    public DeliveryConfigNotFoundException(Long configId) {
        super(String.format(DELIVERY_CONFIG_ID_NOT_FOUND_ERROR_MSG, configId));
    }
}
