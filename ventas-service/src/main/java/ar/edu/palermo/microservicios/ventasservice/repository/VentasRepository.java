package ar.edu.palermo.microservicios.ventasservice.repository;

import ar.edu.palermo.microservicios.ventasservice.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentasRepository extends JpaRepository<Venta, Long> {
    Optional<Venta> findByPatenteVehiculo(String patenteVehiculo);
}
