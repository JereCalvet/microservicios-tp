package ar.edu.palermo.microservicios.stockservice.service;

import ar.edu.palermo.microservicios.stockservice.model.StockResponseDTO;
import ar.edu.palermo.microservicios.stockservice.repository.AlmacenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlmacenServiceImpl implements AlmacenService {

    private final AlmacenRepository almacenRepository;

    public ResponseEntity<StockResponseDTO> checkStock(Long idAlmacen, Long idVehiculo) {
        return null;
    }
}
