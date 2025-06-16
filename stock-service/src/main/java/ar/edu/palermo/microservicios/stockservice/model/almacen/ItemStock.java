package ar.edu.palermo.microservicios.stockservice.model.almacen;

import jakarta.persistence.Embeddable;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ItemStock {

    private Integer cantidad;

    @Override
    public String toString() {
        return "ItemStock{" +
                ", cantidad=" + cantidad +
                '}';
    }
}