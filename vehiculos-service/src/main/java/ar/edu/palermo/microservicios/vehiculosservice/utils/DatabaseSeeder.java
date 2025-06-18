package ar.edu.palermo.microservicios.vehiculosservice.utils;

import ar.edu.palermo.microservicios.vehiculosservice.model.TipoVehiculo;
import ar.edu.palermo.microservicios.vehiculosservice.model.Vehiculo;
import ar.edu.palermo.microservicios.vehiculosservice.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final VehiculoRepository vehiculoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (vehiculoRepository.count() == 0) {
            vehiculoRepository.saveAll(
                    List.of(
                            Vehiculo.builder()
                                    .marca("TOYOTA")
                                    .modelo("ETIOS 1.6")
                                    .tipo(TipoVehiculo.AUTOMOVIL)
                                    .build(),
                            Vehiculo.builder()
                                    .marca("TOYOTA")
                                    .modelo("HILUX 3.0")
                                    .tipo(TipoVehiculo.CAMIONETA)
                                    .build(),
                            Vehiculo.builder()
                                    .marca("IVECOS")
                                    .modelo("EUROCARGO 4.5")
                                    .tipo(TipoVehiculo.CAMION)
                                    .build(),
                            Vehiculo.builder()
                                    .marca("HONDAS")
                                    .modelo("CBR 500")
                                    .tipo(TipoVehiculo.MOTOCICLETA)
                                    .build()
                    )
            );
        }
        System.out.println("Database seeded with initial data.");
    }
}
