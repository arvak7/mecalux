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
        Warehouse warehouse = warehouseHelper.convertDtoToEntity(warehouseHelper.generateUUID(warehouseDto));
        warehouse = warehouseHelper.addRacksIfAreValidated(warehouseDto, warehouse);
        return Optional.of(warehouseHelper.create(warehouse));
    }

    @Override
    public Optional<Warehouse> readWarehouse(UUID uuid) {
        return Optional.ofNullable(warehouseHelper.read(uuid));
    }

    @Override
    public Optional<Warehouse> updateWarehouse(WarehouseDto warehouseDto) {
        Integer idByUuid = warehouseHelper.findIdByUuid(warehouseDto.getUuid());
        Warehouse warehouse = warehouseHelper.convertDtoToEntity(warehouseDto);
        warehouse = warehouseHelper.addRacksIfAreValidated(warehouseDto, warehouse);
        return Optional.of(warehouseHelper.update(warehouse, idByUuid));
    }

    @Override
    public Boolean deleteWarehouse(UUID uuid) {
        Integer idByUuid = warehouseHelper.findIdByUuid(uuid);
        return warehouseHelper.delete(idByUuid);
    }
}
