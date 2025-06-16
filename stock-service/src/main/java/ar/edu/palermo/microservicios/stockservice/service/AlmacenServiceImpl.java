package ar.edu.palermo.microservicios.stockservice.service;

import ar.edu.palermo.microservicios.stockservice.exception.AlmacenNotFoundException;
import ar.edu.palermo.microservicios.stockservice.exception.NotEnoughStockException;
import ar.edu.palermo.microservicios.stockservice.model.almacen.*;
import ar.edu.palermo.microservicios.stockservice.repository.AlmacenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AlmacenServiceImpl implements AlmacenService {

    private final AlmacenRepository almacenRepository;
    private final AlmacenMapper almacenMapper;

    public StockStatusResponseDTO checkStock(Long idAlmacen, Long idVehiculo) {
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
