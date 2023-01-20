package com.mecalux.prueba.rack;

import com.mecalux.prueba.warehouse.Warehouse;
import com.mecalux.prueba.warehouse.WarehouseHelper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class RackServiceImpl implements RackService {

    private final RackHelper rackHelper;

    private final WarehouseHelper warehouseHelper;

    public RackServiceImpl(RackHelper rackHelper, WarehouseHelper warehouseHelper) {
        this.rackHelper = rackHelper;
        this.warehouseHelper = warehouseHelper;
    }

    @Override
    public Optional<Rack> createRack(RackDto rackDto) {
        rackHelper.validateNotExistUuid(rackDto);
        Warehouse warehouseById = warehouseHelper.findWarehouseById(rackDto.getWarehouse_id());
        rackHelper.validateRack(warehouseById.getSize(), warehouseById.getRacks().stream().map(Rack::getType).collect(Collectors.toList()),
                warehouseById.getFamily());
        Rack rack = rackHelper.convertDtoToEntity(rackHelper.generateUuid(rackDto));
        return Optional.of(rackHelper.create(rack));
    }

    @Override
    public Optional<Rack> readRack(UUID uuid) {
        return Optional.ofNullable(rackHelper.read(uuid));
    }

    @Override
    public Optional<Rack> updateRack(RackDto rackDto) {
        Integer idByUuid = rackHelper.findIdByUuid(rackDto.getUuid());
        Warehouse warehouseById = warehouseHelper.findWarehouseById(rackDto.getWarehouse_id());
        rackHelper.validateRack(warehouseById.getSize(), warehouseById.getRacks().stream().map(Rack::getType).collect(Collectors.toList()), warehouseById.getFamily());
        Rack rack = rackHelper.convertDtoToEntity(rackDto);
        return Optional.of(rackHelper.update(rack, idByUuid));
    }

    @Override
    public Boolean deleteRack(UUID uuid) {
        return rackHelper.delete(uuid);
    }
}
