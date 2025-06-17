package ar.edu.palermo.microservicios.stockservice.repository;

import ar.edu.palermo.microservicios.stockservice.model.almacen.Almacen;
import ar.edu.palermo.microservicios.stockservice.model.almacen.TipoAlmacen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
    Optional<Almacen> findBySucursalId(Long sucursalId);
    Optional<Almacen> findByTipo(TipoAlmacen tipo);
}
