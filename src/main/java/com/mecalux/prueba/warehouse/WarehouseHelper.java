package com.mecalux.prueba.warehouse;

import com.mecalux.prueba.common.exception.BadRequestException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Component
@Validated
public class WarehouseHelper {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseConverter warehouseConverter;

    public WarehouseHelper(WarehouseRepository warehouseRepository, WarehouseConverter warehouseConverter) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseConverter = warehouseConverter;
    }

    protected Warehouse create(@NotNull WarehouseDto warehouseDto) {
        return warehouseRepository.save(warehouseConverter.convertDtoToEntity(createUUID(warehouseDto)));
    }

    protected Warehouse update(WarehouseDto warehouseDto, @NotNull Integer id) {
        return warehouseRepository.save(addIdIntoWarehouse(warehouseConverter.convertDtoToEntity(warehouseDto), id));
    }

    protected Warehouse read(@NotNull UUID uuid) {
        return warehouseRepository.getWarehouseByUuid(uuid);
    }

    protected Boolean delete(@NotNull Integer id) {
        return warehouseRepository.deleteWarehouseById(id) > 0;
    }

    protected void validateNotExistUuid(@NotNull WarehouseDto warehouseDto) {
        if (warehouseDto.getUuid() != null) {
            throw new BadRequestException("The uuid should be null in the create operation");
        }
    }

    protected Integer findIdByUuid(@NotNull UUID uuid) {
        Warehouse warehouseByUuid = warehouseRepository.getWarehouseByUuid(uuid);
        if (warehouseByUuid == null) {
            throw new BadRequestException(String.format("The uuid %s don't exist in our system", uuid));
        }
        return warehouseByUuid.getId();
    }

    private Warehouse addIdIntoWarehouse(Warehouse warehouse, Integer id) {
        warehouse.setId(id);
        return warehouse;
    }

    private WarehouseDto createUUID(WarehouseDto warehouseDto) {
        warehouseDto.setUuid(UUID.randomUUID());
        return warehouseDto;
    }

}
