package ar.edu.palermo.microservicios.ventasservice.service;

import ar.edu.palermo.microservicios.ventasservice.config.SucursalConfig;
import ar.edu.palermo.microservicios.ventasservice.exception.NotEnoughStockForSaleException;
import ar.edu.palermo.microservicios.ventasservice.exception.SucursalConfigurationNotFoundException;
import ar.edu.palermo.microservicios.ventasservice.exception.VentaNotFoundException;
import ar.edu.palermo.microservicios.ventasservice.integration.StockClient;
import ar.edu.palermo.microservicios.ventasservice.model.*;
import ar.edu.palermo.microservicios.ventasservice.repository.VentasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VentasServiceImpl implements VentasService {

    private final StockClient stockClient;
    private final SucursalConfig sucursalConfig;
    private final VentasRepository ventasRepository;
    private final VentaMapper ventaMapper;

    @Override
    public List<VentaResponseDTO> findAll() {
        List<Venta> sucursales = ventasRepository.findAll();
        return sucursales.stream()
                .map(ventaMapper::toResponseDTO)
                .toList();
    }

    @Override
    public VentaResponseDTO findById(Long id) {
        Venta ventaFound = ventasRepository.findById(id)
                .orElseThrow(() -> new VentaNotFoundException(id));
        return ventaMapper.toResponseDTO(ventaFound);
    }

    @Override
    public VentaResponseDTO findByPatenteVehiculo(String patenteVehiculo) {
        Venta ventaFound = ventasRepository.findByPatenteVehiculo(patenteVehiculo)
                .orElseThrow(() -> new VentaNotFoundException(patenteVehiculo));
        return ventaMapper.toResponseDTO(ventaFound);
    }

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
                .patenteVehiculo(String.format(
                        "%s-%s-%s",
                        datosFacturaDTO.nombreCliente().substring(0, 3).toUpperCase(),
                        datosFacturaDTO.dniCliente().substring(0, 3),
                        "ARG"
                ))
                .observaciones(String.format(
                        "Venta realizada con éxito. Tiempo de espera estimado: %d días.",
                        diasEspera
                ))
                .build();
        ventasRepository.save(factura);

        try {
            stockClient.removeStock(
                    idAlmacenDeLaSucursal,
                    datosFacturaDTO.vehiculoId(),
                    datosFacturaDTO.cantidad()
            );
        } catch (Exception e) {
            System.out.println("Error al eliminar stock: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private StockRequestResponseDTO solicitarStock(Long vehiculoId, Integer cantidad) {
        String sucursalId = sucursalConfig.getSucursalId();
        if (sucursalId.isBlank()) {
            throw new SucursalConfigurationNotFoundException();
        }

        StockVerifyAvailabilityResponseDTO availabilityStockResponse = null;
        try {
            availabilityStockResponse = stockClient.verifyAvailability(Long.valueOf(sucursalId), vehiculoId, cantidad);
        } catch (NumberFormatException e) {
            System.out.println("Error verificar diponibilidad de stock: " + e.getMessage());
            throw new RuntimeException(e);
        }
        if (!availabilityStockResponse.isAvailable()) {
            throw new NotEnoughStockForSaleException();
        }

        StockRequestResponseDTO dto = null;
        try {
            dto = stockClient.stockRequest(
                    new StockRequestDTO(Long.valueOf(sucursalId), vehiculoId, cantidad)
            );
        } catch (NumberFormatException e) {
            System.out.println("Error al solicitar stock: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return dto;
    }
}
