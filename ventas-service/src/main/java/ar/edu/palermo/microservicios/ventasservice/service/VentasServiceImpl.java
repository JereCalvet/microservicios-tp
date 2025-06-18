package ar.edu.palermo.microservicios.ventasservice.service;

import ar.edu.palermo.microservicios.ventasservice.config.SucursalConfig;
import ar.edu.palermo.microservicios.ventasservice.exception.NotEnoughStockForSaleException;
import ar.edu.palermo.microservicios.ventasservice.exception.SucursalConfigurationNotFoundException;
import ar.edu.palermo.microservicios.ventasservice.integration.StockClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VentasServiceImpl {

    private final StockClient stockClient;
    private final SucursalConfig sucursalConfig;

    @Transactional
    public void realizarVenta(Long clienteId, Long vehiculoId, Long vendedorId, Integer cantidad) {

        String sucursalId = sucursalConfig.getSucursalId();
        if (sucursalId.isBlank()) {
            throw new SucursalConfigurationNotFoundException();
        }

        var availabilityStockResponse = stockClient.verifyAvailability(Long.valueOf(sucursalId), vehiculoId, cantidad);
        if (!availabilityStockResponse.isAvailable()) {
            throw new NotEnoughStockForSaleException();
        }

        stockClient.stockRequest(Long.valueOf(sucursalId), vehiculoId, cantidad);
        cusmimos/retiramos el stock del la sucursal (local)
        fin de venta.
    }
}
