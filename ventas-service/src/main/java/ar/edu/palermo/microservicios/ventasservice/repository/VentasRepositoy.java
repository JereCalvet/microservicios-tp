package ar.edu.palermo.microservicios.ventasservice.repository;

import ar.edu.palermo.microservicios.ventasservice.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasRepositoy extends JpaRepository<Venta, Long> {
}
