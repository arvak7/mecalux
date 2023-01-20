package com.mecalux.prueba.warehouse;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.base.Racks;
import com.mecalux.prueba.common.exception.BadRequestException;
import com.mecalux.prueba.common.exception.NotFoundException;
import com.mecalux.prueba.common.validate.RackValidation;
import com.mecalux.prueba.rack.Rack;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Validated
public class WarehouseHelper {

    private final WarehouseRepository warehouseRepository;

    private final WarehouseConverter warehouseConverter;

    private final RackValidation rackValidation;

    public WarehouseHelper(WarehouseRepository warehouseRepository, WarehouseConverter warehouseConverter, RackValidation rackValidation) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseConverter = warehouseConverter;
        this.rackValidation = rackValidation;
    }

    public Warehouse findWarehouseById(@NotNull Integer id) {
        return warehouseRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    protected Warehouse create(@NotNull Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    protected Warehouse read(@NotNull UUID uuid) {
        return warehouseRepository.getWarehouseByUuid(uuid);
    }

    protected Warehouse update(@NotNull Warehouse warehouse, @NotNull Integer id) {
        return warehouseRepository.save(addIdIntoWarehouse(warehouse, id));
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
        List<Rack> racks = new ArrayList<>();
        if (!CollectionUtils.isEmpty(warehouseDto.getRacks())) {
            rackValidation.validateRacksSize(warehouseDto.getRacks().size(), warehouse.getSize());
            rackValidation.validateRacksFamily(warehouseDto.getRacks().stream().map(Racks::valueOf).collect(Collectors.toList()), Family.valueOf(warehouseDto.getFamily()));
            warehouseDto.getRacks().forEach(rack -> racks.add(createRack(Racks.valueOf(rack))));
            warehouse.setRacks(racks);
        } else {
            warehouse.setRacks(new ArrayList<>());
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
        rack.setType(rackEnum);
        return rack;
    }

}
