package ar.edu.palermo.microservicios.stockservice.repository;

import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryConfigRepository extends JpaRepository<DeliveryConfig, Long> {
    /**
     * Método para encontrar una configuración de entrega
     * desde un almacén específico hacia otro almacén específico.
     */
    Optional<DeliveryConfig> findByDesdeAlmacenIdAndHastaAlmacenId(Long desdeAlmacenId, Long hastaAlmacenId);
}
