package ar.edu.palermo.microservicios.ventasservice.service;

import ar.edu.palermo.microservicios.ventasservice.config.SucursalConfig;
import ar.edu.palermo.microservicios.ventasservice.exception.NotEnoughStockForSaleException;
import ar.edu.palermo.microservicios.ventasservice.exception.SucursalConfigurationNotFoundException;
import ar.edu.palermo.microservicios.ventasservice.integration.StockClient;
import ar.edu.palermo.microservicios.ventasservice.model.DatosFacturaDTO;
import ar.edu.palermo.microservicios.ventasservice.model.StockRequestDTO;
import ar.edu.palermo.microservicios.ventasservice.model.StockRequestResponseDTO;
import ar.edu.palermo.microservicios.ventasservice.model.Venta;
import ar.edu.palermo.microservicios.ventasservice.repository.VentasRepositoy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class VentasServiceImpl {

    private final StockClient stockClient;
    private final SucursalConfig sucursalConfig;
    private final VentasRepositoy ventasRepository;

    @Transactional
    public void realizarVenta(DatosFacturaDTO datosFacturaDTO) {
        StockRequestResponseDTO dto = solicitarStock(datosFacturaDTO.vehiculoId(), datosFacturaDTO.cantidad());
        Integer diasEspera = dto.tiempoEntregaEstimadoEnDias();
        Long idAlmacenDeLaSucursal = dto.almacenDestinoId();

        // todo: hacer fetch de los datos del cliente, vehículo y vendedor para completar la factura
        // el vendedor lo sacaria del usuario logeado/security.
        Venta factura = Venta.builder()
                .fechaVenta(LocalDate.now())
                .precioUnitario(datosFacturaDTO.precioUnitario())
                .sucursalId(Long.valueOf(sucursalConfig.getSucursalId()))
                .clienteId(1L)
                .vehiculoId(datosFacturaDTO.vehiculoId())
                .vendedorId(1L)
                .nombreCliente(datosFacturaDTO.nombreCliente())
                .dniCliente(datosFacturaDTO.dniCliente())
                .cantidad(datosFacturaDTO.cantidad())
                .totalVenta(datosFacturaDTO.cantidad() * datosFacturaDTO.precioUnitario())
                .observaciones(String.format(
                        "Venta realizada con éxito. Tiempo de espera estimado: %d días.",
                        diasEspera
                ))
                .build();
        ventasRepository.save(factura);

        stockClient.removeStock(
                idAlmacenDeLaSucursal,
                datosFacturaDTO.vehiculoId(),
                datosFacturaDTO.cantidad()
        );
    }

    private StockRequestResponseDTO solicitarStock(Long vehiculoId, Integer cantidad) {
        String sucursalId = sucursalConfig.getSucursalId();
        if (sucursalId.isBlank()) {
            throw new SucursalConfigurationNotFoundException();
        }

        var availabilityStockResponse = stockClient.verifyAvailability(Long.valueOf(sucursalId), vehiculoId, cantidad);
        if (!availabilityStockResponse.isAvailable()) {
            throw new NotEnoughStockForSaleException();
        }

        return stockClient.stockRequest(
                new StockRequestDTO(Long.valueOf(sucursalId), vehiculoId, cantidad)
        );
    }
}
