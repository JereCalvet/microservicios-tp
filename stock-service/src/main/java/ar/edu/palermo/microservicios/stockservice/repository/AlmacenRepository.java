package ar.edu.palermo.microservicios.stockservice.repository;

import ar.edu.palermo.microservicios.stockservice.model.almacen.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}
