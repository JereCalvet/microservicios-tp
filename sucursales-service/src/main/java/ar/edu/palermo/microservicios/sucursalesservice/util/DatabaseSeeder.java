package ar.edu.palermo.microservicios.sucursalesservice.util;

import ar.edu.palermo.microservicios.sucursalesservice.model.Pais;
import ar.edu.palermo.microservicios.sucursalesservice.model.Provincia;
import ar.edu.palermo.microservicios.sucursalesservice.model.Sucursal;
import ar.edu.palermo.microservicios.sucursalesservice.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final SucursalRepository sucursalRepository;

    @Override
    public void run(String... args) throws Exception {
        if (sucursalRepository.count() == 0) {
            sucursalRepository.saveAll(
                    List.of(
                            Sucursal.builder()
                                    .pais(Pais.ARGENTINA)
                                    .provincia(Provincia.BUENOS_AIRES)
                                    .localidad("Buenos Aires")
                                    .direccion("Avenida Corrientes 1234")
                                    .nombreFicticio("TOYOTA CASA CENTRAL")
                                    .fechaApertura(LocalDate.of(1998, 6, 8))
                                    .build(),
                            Sucursal.builder()
                                    .pais(Pais.ARGENTINA)
                                    .provincia(Provincia.TIERRA_DEL_FUEGO)
                                    .localidad("Rio Grande")
                                    .direccion("Avenida San Martin 123")
                                    .nombreFicticio("TOYOTA RIO GRANDE")
                                    .fechaApertura(LocalDate.of(2024, 1, 15))
                                    .build(),
                            Sucursal.builder()
                                    .pais(Pais.ARGENTINA)
                                    .provincia(Provincia.TIERRA_DEL_FUEGO)
                                    .localidad("Ushuaia")
                                    .direccion("Avenida Maipú 456")
                                    .nombreFicticio("TOYOTA USHUAIA")
                                    .fechaApertura(LocalDate.of(2023, 6, 22))
                                    .build()
                    )
            );
        }
        System.out.println("Database seeded with initial data.");
    }
}
