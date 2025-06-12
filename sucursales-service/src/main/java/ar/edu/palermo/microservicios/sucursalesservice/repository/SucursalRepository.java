package ar.edu.palermo.microservicios.sucursalesservice.repository;

import ar.edu.palermo.microservicios.sucursalesservice.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
