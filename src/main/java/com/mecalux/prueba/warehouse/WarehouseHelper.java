package com.mecalux.prueba.warehouse;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.base.Racks;
import com.mecalux.prueba.common.exception.BadRequestException;
import com.mecalux.prueba.rack.Rack;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;

@Component
@Validated
public class WarehouseHelper {

    private final WarehouseRepository warehouseRepository;

    private final WarehouseConverter warehouseConverter;

    BiConsumer<Set<String>, Family> validateRacksByFamily = (racks, family) -> racks.forEach(r -> {
        if (!family.getRacks().contains(r)) {
            throw new BadRequestException(String.format("The %s rack is not valid in the %s family. The valid are %s", r, family, family.getRacks()));
        }
    });

    BiConsumer<Integer, Integer> validateRacksSize = (rackSize, allowedSize) -> {
        if (rackSize > allowedSize) throw new BadRequestException(String.format("The rack size %d is greater than the maximum warehouse size %d", rackSize, allowedSize));
    };

    public WarehouseHelper(WarehouseRepository warehouseRepository, WarehouseConverter warehouseConverter) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseConverter = warehouseConverter;
    }

    protected Warehouse create(@NotNull Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    protected Warehouse update(@NotNull Warehouse warehouse, @NotNull Integer id) {
        return warehouseRepository.save(addIdIntoWarehouse(warehouse, id));
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

    protected Warehouse addRacksIfAreValidated(@NotNull WarehouseDto warehouseDto, @NotNull Warehouse warehouse) {
        Set<Rack> racks = new HashSet<>();
        if (!CollectionUtils.isEmpty(warehouseDto.getRacks())) {
            validateRacksSize.accept(warehouseDto.getRacks().size(), warehouse.getSize());
            validateRacksByFamily.accept(warehouseDto.getRacks(), Family.valueOf(warehouseDto.getFamily()));
            warehouseDto.getRacks().forEach(rack -> racks.add(createRack(Racks.valueOf(rack))));
            warehouse.setRacks(racks);
        } else {
            warehouse.setRacks(new HashSet<>());
        }
        return warehouse;
    }

    protected Warehouse convertDtoToEntity(WarehouseDto warehouseDto) {
        return warehouseConverter.convertDtoToEntity(warehouseDto);
    }

    protected WarehouseDto generateUUID(WarehouseDto warehouseDto) {
        warehouseDto.setUuid(UUID.randomUUID());
        return warehouseDto;
    }

    private Warehouse addIdIntoWarehouse(Warehouse warehouse, Integer id) {
        warehouse.setId(id);
        return warehouse;
    }

    private Rack createRack(Racks rackEnum) {
        Rack rack = new Rack();
        rack.setUuid(UUID.randomUUID());
        rack.setType(rackEnum.toString());
        return rack;
    }

}
