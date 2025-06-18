package ar.edu.palermo.microservicios.mecanicaservice.configuration;

import ar.edu.palermo.microservicios.mecanicaservice.model.TipoVehiculo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties(prefix = "garantia")
public class GarantiaConfig {

    private Map<String, Integer> anios;

    public Integer getPorTipo(TipoVehiculo tipo) {
        return anios.get(tipo.name().toLowerCase());
    }
}
