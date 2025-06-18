package ar.edu.palermo.microservicios.mecanicaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MecanicaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MecanicaServiceApplication.class, args);
    }

}
