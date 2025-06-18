package ar.edu.palermo.microservicios.mecanicaservice.repository;

import ar.edu.palermo.microservicios.mecanicaservice.model.ServicioMecanico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioMecanicoRepository extends JpaRepository<ServicioMecanico, Long> {

}
