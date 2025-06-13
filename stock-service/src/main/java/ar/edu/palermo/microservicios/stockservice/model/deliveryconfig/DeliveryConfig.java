package ar.edu.palermo.microservicios.stockservice.model.deliveryconfig;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryConfig {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private Long desdeAlmacenId;

    @NotNull
    private Long hastaAlmacenId;

    @NotNull
    private Integer tiempoEntregaEstimadoEnDias;
}
