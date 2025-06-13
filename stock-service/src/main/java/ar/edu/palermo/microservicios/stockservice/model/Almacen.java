package ar.edu.palermo.microservicios.stockservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long sucursalId;

    @Size(min = 3, max = 50, message = "El nombre del almacén debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoAlmacen tipo;

    @ElementCollection
    @CollectionTable(name = "stock", joinColumns = @JoinColumn(name = "almacen_id"))
    @MapKeyColumn(name = "vehiculo_id")
    private Map<Long, ItemStock> stock = new HashMap<>();


}
