package ar.edu.palermo.microservicios.ventasservice.service;

import ar.edu.palermo.microservicios.ventasservice.integration.StockClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VentasServiceImpl {

    private final StockClient stockClient;

    public void realizarVenta(Long clienteId, Long sucursalId, Long vehiculoId, Long vendedorId, Integer cantidad) {
        var stockResponse = stockClient.checkStock(sucursalId, vehiculoId);

        if (stockResponse.cantidadVehiculos() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock disponible para realizar la venta.");
        }

        // Aquí se podría continuar con el proceso de venta, como guardar la venta en la base de datos
        // y actualizar el stock en el servicio de stock.

        // Ejemplo de lógica adicional:
        // Venta venta = new Venta(clienteId, sucursalId, vehiculoId, vendedorId, cantidad);
        // ventaRepository.save(venta);

        // Actualizar el stock
        // stockClient.updateStock(sucursalId, vehiculoId, -cantidad);
    }
}
