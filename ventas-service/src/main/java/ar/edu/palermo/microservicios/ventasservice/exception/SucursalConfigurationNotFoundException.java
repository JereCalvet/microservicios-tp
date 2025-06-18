package ar.edu.palermo.microservicios.ventasservice.exception;


public class SucursalConfigurationNotFoundException extends RuntimeException {
    private static final String CONFIGURATION_ERROR_MSG = "Hay que configurar el parametro \"sucursal.id=\" en el archivo application.properties.";

    public SucursalConfigurationNotFoundException() {
        super(CONFIGURATION_ERROR_MSG);
    }
}


