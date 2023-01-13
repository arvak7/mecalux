package com.mecalux.prueba.warehouse;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseHelper warehouseHelper;

    public WarehouseServiceImpl(WarehouseHelper warehouseHelper) {
        this.warehouseHelper = warehouseHelper;
    }

    @Override
    public Optional<Warehouse> createWarehouse(WarehouseDto warehouseDto) {
        warehouseHelper.validateNotExistUuid(warehouseDto);
        return Optional.of(warehouseHelper.create(warehouseDto));
    }

    @Override
    public Optional<Warehouse> readWarehouse(UUID uuid) {
        return Optional.ofNullable(warehouseHelper.read(uuid));
    }

    @Override
    public Optional<Warehouse> updateWarehouse(WarehouseDto warehouseDto) {
        Integer idByUuid = warehouseHelper.findIdByUuid(warehouseDto.getUuid());
        return Optional.of(warehouseHelper.update(warehouseDto, idByUuid));
    }

    @Override
    public Boolean deleteWarehouse(UUID uuid) {
        Integer idByUuid = warehouseHelper.findIdByUuid(uuid);
        return warehouseHelper.delete(idByUuid);
    }
}
