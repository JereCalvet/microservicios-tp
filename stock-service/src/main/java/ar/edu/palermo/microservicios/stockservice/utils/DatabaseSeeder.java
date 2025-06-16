package ar.edu.palermo.microservicios.stockservice.utils;

import ar.edu.palermo.microservicios.stockservice.model.almacen.Almacen;
import ar.edu.palermo.microservicios.stockservice.model.almacen.TipoAlmacen;
import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfig;
import ar.edu.palermo.microservicios.stockservice.repository.AlmacenRepository;
import ar.edu.palermo.microservicios.stockservice.repository.DeliveryConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final DeliveryConfigRepository deliveryConfigRepository;
    private final AlmacenRepository almacenRepository;

    @Override
    public void run(String... args) throws Exception {
        if (almacenRepository.count() == 0 && deliveryConfigRepository.count() == 0) {
            Almacen almacenCentral = Almacen.builder()
                    .sucursalId(1L)
                    .nombre("Almacen - TOYOTA CASA CENTRAL")
                    .tipo(TipoAlmacen.CENTRAL)
                    .build();
            Almacen almacenRioGrande = Almacen.builder()
                    .sucursalId(2L)
                    .nombre("Almacen - TOYOTA RIO GRANDE")
                    .tipo(TipoAlmacen.LOCAL)
                    .build();
            Almacen almacenUshuaia = Almacen.builder()
                    .sucursalId(3L)
                    .nombre("Almacen - TOYOTA USHUAIA")
                    .tipo(TipoAlmacen.LOCAL)
                    .build();
            almacenRepository.saveAll(
                    List.of(
                            almacenCentral,
                            almacenRioGrande,
                            almacenUshuaia
                    )
            );

            DeliveryConfig configHaciaRioGrande = DeliveryConfig.builder()
                    .desdeAlmacenId(almacenCentral.getId())
                    .hastaAlmacenId(almacenRioGrande.getId())
                    .tiempoEntregaEstimadoEnDias(3)
                    .build();
            DeliveryConfig configHaciaUshuaia = DeliveryConfig.builder()
                    .desdeAlmacenId(almacenCentral.getId())
                    .hastaAlmacenId(almacenUshuaia.getId())
                    .tiempoEntregaEstimadoEnDias(5)
                    .build();

            deliveryConfigRepository.saveAll(
                    List.of(
                            configHaciaRioGrande,
                            configHaciaUshuaia
                    )
            );
        }
        System.out.println("Database seeded with initial data.");
    }
}
