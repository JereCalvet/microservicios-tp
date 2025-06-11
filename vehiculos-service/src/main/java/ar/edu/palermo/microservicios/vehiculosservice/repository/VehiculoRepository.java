package ar.edu.palermo.microservicios.vehiculosservice.repository;

import ar.edu.palermo.microservicios.vehiculosservice.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}
