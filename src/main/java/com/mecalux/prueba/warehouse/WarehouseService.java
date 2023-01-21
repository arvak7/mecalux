package com.mecalux.prueba.warehouse;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface WarehouseService {

    Optional<Warehouse> createWarehouse(WarehouseDto warehouseDto);

    Optional<Warehouse> readWarehouse(UUID uuid);

    Optional<Warehouse> updateWarehouse(WarehouseDto warehouseDto);

    Boolean deleteWarehouse(UUID uuid);
    Optional<List<Warehouse>> findAll();
}
