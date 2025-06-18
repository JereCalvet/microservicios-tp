package ar.edu.palermo.microservicios.mecanicaservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties(prefix = "anios-garantia")
public class GarantiaConfig {

    private String automovil;
    private String motocicleta;
    private String camioneta;
    private String camion;
}
