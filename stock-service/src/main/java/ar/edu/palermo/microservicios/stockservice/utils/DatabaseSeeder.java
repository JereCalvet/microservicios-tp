package ar.edu.palermo.microservicios.stockservice.utils;

import ar.edu.palermo.microservicios.stockservice.model.almacen.Almacen;
import ar.edu.palermo.microservicios.stockservice.model.almacen.ItemStock;
import ar.edu.palermo.microservicios.stockservice.model.almacen.TipoAlmacen;
import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfig;
import ar.edu.palermo.microservicios.stockservice.repository.AlmacenRepository;
import ar.edu.palermo.microservicios.stockservice.repository.DeliveryConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
                    .stock(Map.of(
                            1L, new ItemStock(50), // ETIOS 1.6
                            2L, new ItemStock(50), // HILUX 3.0
                            3L, new ItemStock(50),  // IVECO 4.5
                            4L, new ItemStock(50) // CBR 500
                    ))
                    .build();
            Almacen almacenRioGrande = Almacen.builder()
                    .sucursalId(2L)
                    .nombre("Almacen - TOYOTA RIO GRANDE")
                    .tipo(TipoAlmacen.LOCAL)
                    .stock(Map.of(
                            1L, new ItemStock(1), // ETIOS 1.6
                            2L, new ItemStock(1), // HILUX 3.0
                            3L, new ItemStock(1),  // IVECO 4.5
                            4L, new ItemStock(1) // CBR 500
                    ))
                    .build();
            Almacen almacenUshuaia = Almacen.builder()
                    .sucursalId(3L)
                    .nombre("Almacen - TOYOTA USHUAIA")
                    .tipo(TipoAlmacen.LOCAL)
                    .stock(Map.of(
                            1L, new ItemStock(2), // ETIOS 1.6
                            2L, new ItemStock(2),  // HILUX 3.0
                            3L, new ItemStock(2),  // IVECO 4.5
                            4L, new ItemStock(2) // CBR 500
                    ))
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
