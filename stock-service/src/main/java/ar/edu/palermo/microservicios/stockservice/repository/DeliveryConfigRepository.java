package ar.edu.palermo.microservicios.stockservice.repository;

import ar.edu.palermo.microservicios.stockservice.model.deliveryconfig.DeliveryConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryConfigRepository extends JpaRepository<DeliveryConfig, Long> {

}
