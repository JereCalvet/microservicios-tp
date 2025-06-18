package ar.edu.palermo.microservicios.mecanicaservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Servicio mecánico no encontrado")
public class ServicioMecanicoNotFoundException extends RuntimeException {
    private static final String SERVICIO_MECANICO_ID_NOT_FOUND_ERROR_MSG = "Servicio mecánico  id %d not found.";

    public ServicioMecanicoNotFoundException(Long servicioMecanicoId) {
        super(String.format(SERVICIO_MECANICO_ID_NOT_FOUND_ERROR_MSG, servicioMecanicoId));
    }
}