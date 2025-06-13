package ar.edu.palermo.microservicios.stockservice.repository;

import ar.edu.palermo.microservicios.stockservice.model.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}
