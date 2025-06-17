package ar.edu.palermo.microservicios.ventasservice.utils;

import ar.edu.palermo.microservicios.ventasservice.service.VentasServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final VentasServiceImpl ventasService;
    @Override
    public void run(String... args) throws Exception {
        ventasService.realizarVenta(
                1L, // clienteId
                1L, // sucursalId
                1L, // vehiculoId
                1L, // vendedorId
                2 // cantidad
        );

    }
}
