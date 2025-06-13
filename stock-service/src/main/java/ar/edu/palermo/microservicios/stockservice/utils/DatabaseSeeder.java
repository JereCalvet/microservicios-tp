package ar.edu.palermo.microservicios.stockservice.utils;

import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfig;
import ar.edu.palermo.microservicios.stockservice.repository.DeliveryConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final DeliveryConfigRepository deliveryConfigRepository;

    @Override
    public void run(String... args) throws Exception {
        if (deliveryConfigRepository.count() == 0) {
            //verificar id de central
            DeliveryConfig configHaciaRioGrande = DeliveryConfig.builder()
                    .desdeAlmacenId(1L)
                    .hastaAlmacenId(2L)
                    .tiempoEntregaEstimadoEnDias(3)
                    .build();
            DeliveryConfig configHaciaUshuaia = DeliveryConfig.builder()
                    .desdeAlmacenId(1L)
                    .hastaAlmacenId(3L)
                    .tiempoEntregaEstimadoEnDias(5)
                    .build();
            DeliveryConfig configHaciaSanBernardo = DeliveryConfig.builder()
                    .desdeAlmacenId(1L)
                    .hastaAlmacenId(4L)
                    .tiempoEntregaEstimadoEnDias(7)
                    .build();

            deliveryConfigRepository.saveAll(
                    List.of(
                            configHaciaRioGrande,
                            configHaciaUshuaia,
                            configHaciaSanBernardo
                    )
            );
        }
        System.out.println("Database seeded with initial data.");
    }
}
