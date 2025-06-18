package ar.edu.palermo.microservicios.stockservice.service;

import ar.edu.palermo.microservicios.stockservice.exception.AlmacenCentralNotFoundException;
import ar.edu.palermo.microservicios.stockservice.exception.AlmacenNotFoundException;
import ar.edu.palermo.microservicios.stockservice.exception.NotEnoughStockException;
import ar.edu.palermo.microservicios.stockservice.exception.NotEnoughStockToTransferException;
import ar.edu.palermo.microservicios.stockservice.model.almacen.*;
import ar.edu.palermo.microservicios.stockservice.repository.AlmacenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AlmacenServiceImpl implements AlmacenService {

    private final DeliveryConfigService deliveryConfigService;
    private final AlmacenRepository almacenRepository;
    private final AlmacenMapper almacenMapper;

    public StockVerifyAvailabilityResponseDTO verifyAvailability(Long idSucursal, Long idVehiculo, Integer cantidad) {
        Almacen almacenLocal = almacenRepository.findBySucursalId(idSucursal)
                .orElseThrow(() -> new AlmacenNotFoundException(idSucursal));
        Map<Long, ItemStock> stockLocal = almacenLocal.getStock();
        ItemStock itemStockLocal = stockLocal.getOrDefault(idVehiculo, new ItemStock(0));
        int disponibleLocal = itemStockLocal.getCantidad();

        Almacen almacenCentral = almacenRepository.findByTipo(TipoAlmacen.CENTRAL)
                .orElseThrow(AlmacenCentralNotFoundException::new);
        Map<Long, ItemStock> stockCentral = almacenCentral.getStock();
        ItemStock itemStockCentral = stockCentral.getOrDefault(idVehiculo, new ItemStock(0));
        int disponibleCentral = itemStockCentral.getCantidad();

        int totalDisponible = disponibleLocal + disponibleCentral;

        return new StockVerifyAvailabilityResponseDTO(
                cantidad,
                totalDisponible >= cantidad,
                almacenLocal.getId(),
                disponibleLocal,
                almacenCentral.getId(),
                disponibleCentral,
                idVehiculo
        );
    }

    public StockRequestResponseDTO stockRequest(Long idSucursal, Long idVehiculo, Integer cantidad) {
        Almacen almacenLocal = almacenRepository.findBySucursalId(idSucursal)
                .orElseThrow(() -> new AlmacenNotFoundException(idSucursal));
        Map<Long, ItemStock> stockLocal = almacenLocal.getStock();
        ItemStock cantidadEnStockLocal = stockLocal.getOrDefault(idVehiculo, new ItemStock(0));

        if (cantidadEnStockLocal.getCantidad() >= cantidad) {
            return new StockRequestResponseDTO(
                    almacenLocal.getId(),
                    0);
        }

        Almacen almacenCentral = almacenRepository.findByTipo(TipoAlmacen.CENTRAL)
                .orElseThrow(AlmacenCentralNotFoundException::new);
        Map<Long, ItemStock> stockCentral = almacenCentral.getStock();
        ItemStock cantidadEnStockCentral = stockCentral.getOrDefault(idVehiculo, new ItemStock(0));

        if (cantidadEnStockCentral.getCantidad() >= cantidad) {
            this.transferStock(almacenCentral.getId(), almacenLocal.getId(), idVehiculo, cantidad);
            Integer tiempoEntregaEstimadoEnDias = this.calculateTransferTimeFromConfig(almacenCentral.getId(), almacenLocal.getId());
            return new StockRequestResponseDTO(
                    almacenLocal.getId(),
                    tiempoEntregaEstimadoEnDias
            );
        }

        throw new NotEnoughStockException(cantidadEnStockCentral.getCantidad());
    }

    private void transferStock(Long idAlmacenOrigen, Long idAlmacenDestino, Long idVehiculo, Integer cantidad) {
        Almacen almacenOrigen = almacenRepository.findById(idAlmacenOrigen)
                .orElseThrow(() -> new AlmacenNotFoundException(idAlmacenOrigen));
        Map<Long, ItemStock> stockOrigen = almacenOrigen.getStock();
        ItemStock itemStockOrigen = stockOrigen.getOrDefault(idVehiculo, new ItemStock(0));

        if (itemStockOrigen.getCantidad() < cantidad) {
            throw new NotEnoughStockToTransferException(itemStockOrigen.getCantidad());
        }

        itemStockOrigen.setCantidad(itemStockOrigen.getCantidad() - cantidad);
        stockOrigen.put(idVehiculo, itemStockOrigen);
        almacenOrigen.setStock(stockOrigen);
        almacenRepository.save(almacenOrigen);

        Almacen almacenDestino = almacenRepository.findById(idAlmacenDestino)
                .orElseThrow(() -> new AlmacenNotFoundException(idAlmacenDestino));
        Map<Long, ItemStock> stockDestino = almacenDestino.getStock();
        ItemStock itemStockDestino = stockDestino.getOrDefault(idVehiculo, new ItemStock(0));
        itemStockDestino.setCantidad(itemStockDestino.getCantidad() + cantidad);
        stockDestino.put(idVehiculo, itemStockDestino);
        almacenDestino.setStock(stockDestino);
        almacenRepository.save(almacenDestino);
    }

    private Integer calculateTransferTimeFromConfig(Long idAlmacenOrigen, Long idAlmacenDestino) {
       return deliveryConfigService.fromAlmacenIdToAlmacenId(idAlmacenOrigen, idAlmacenDestino)
               .tiempoEntregaEstimadoEnDias();
    }

    public StockStatusResponseDTO checkStockSucursal(Long idSucursal, Long idVehiculo) {
        Almacen almacenLocal = almacenRepository.findBySucursalId(idSucursal)
                .orElseThrow(() -> new AlmacenNotFoundException(idSucursal));
        Map<Long, ItemStock> stockLocal = almacenLocal.getStock();
        ItemStock itemStock = stockLocal.getOrDefault(idVehiculo, new ItemStock(0));
        Integer cantidadVehiculos = itemStock.getCantidad();

        return new StockStatusResponseDTO(almacenLocal.getId(), idVehiculo, cantidadVehiculos);
    }

    public StockStatusResponseDTO checkStockAlmacen(Long idAlmacen, Long idVehiculo) {
        Almacen almacenFound = almacenRepository.findById(idAlmacen)
                .orElseThrow(() -> new AlmacenNotFoundException(idAlmacen));
        Map<Long, ItemStock> stock = almacenFound.getStock();
        ItemStock itemStock = stock.getOrDefault(idVehiculo, new ItemStock(0));
        Integer cantidadVehiculos = itemStock.getCantidad();

        return new StockStatusResponseDTO(idAlmacen, idVehiculo, cantidadVehiculos);
    }

    public StockStatusResponseDTO addStock(Long idAlmacen, Long idVehiculo, int cantidad) {
        Almacen almacenFound = almacenRepository.findById(idAlmacen)
                .orElseThrow(() -> new AlmacenNotFoundException(idAlmacen));
        Map<Long, ItemStock> stock = almacenFound.getStock();
        ItemStock itemStock = stock.getOrDefault(idVehiculo, new ItemStock(0));
        itemStock.setCantidad(itemStock.getCantidad() + cantidad);
        stock.put(idVehiculo, itemStock);
        almacenFound.setStock(stock);
        almacenRepository.save(almacenFound);

        return new StockStatusResponseDTO(idAlmacen, idVehiculo, itemStock.getCantidad());
    }

    public StockStatusResponseDTO removeStock(Long idAlmacen, Long idVehiculo, Integer cantidad) {
        Almacen almacenFound = almacenRepository.findById(idAlmacen)
                .orElseThrow(() -> new AlmacenNotFoundException(idAlmacen));
        Map<Long, ItemStock> stock = almacenFound.getStock();
        ItemStock itemStock = stock.getOrDefault(idVehiculo, new ItemStock(0));
        if (itemStock.getCantidad() < cantidad) {
            throw new NotEnoughStockException(itemStock.getCantidad());
        }

        itemStock.setCantidad(itemStock.getCantidad() - cantidad);
        stock.put(idVehiculo, itemStock);
        almacenFound.setStock(stock);
        almacenRepository.save(almacenFound);
        return new StockStatusResponseDTO(idAlmacen, idVehiculo, itemStock.getCantidad());
    }

    @Override
    public AlmacenResponseDTO save(AlmacenCreateDTO dto) {
        Almacen entity = almacenMapper.toEntity(dto);
        Almacen saved = almacenRepository.save(entity);
        return almacenMapper.toResponseDTO(saved);
    }

    @Override
    public List<AlmacenResponseDTO> findAll() {
        List<Almacen> almacenes = almacenRepository.findAll();
        return almacenes.stream()
                .map(almacenMapper::toResponseDTO)
                .toList();
    }

    @Override
    public AlmacenResponseDTO findById(Long id) {
        Almacen almacenFound = almacenRepository.findById(id)
                .orElseThrow(() -> new AlmacenNotFoundException(id));
        return almacenMapper.toResponseDTO(almacenFound);
    }

    @Override
    public AlmacenResponseDTO update(Long id, AlmacenUpdateDTO dto) {
        Almacen almacenToUpdate = almacenRepository.findById(id)
                .orElseThrow(() -> new AlmacenNotFoundException(id));
        almacenMapper.toEntity(dto, almacenToUpdate);
        Almacen updated = almacenRepository.save(almacenToUpdate);
        return almacenMapper.toResponseDTO(updated);
    }

    @Override
    public AlmacenResponseDTO update(Long id, AlmacenPatchDTO dto) {
        Almacen almacenToUpdate = almacenRepository.findById(id)
                .orElseThrow(() -> new AlmacenNotFoundException(id));
        almacenMapper.toEntity(dto, almacenToUpdate);
        Almacen updated = almacenRepository.save(almacenToUpdate);
        return almacenMapper.toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Almacen almacenToDelete = almacenRepository.findById(id)
                .orElseThrow(() -> new AlmacenNotFoundException(id));
        almacenRepository.delete(almacenToDelete);
    }
}
