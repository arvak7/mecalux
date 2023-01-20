package com.mecalux.prueba.warehouse;

import com.mecalux.prueba.common.exception.NotFoundException;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouse")
@Tag(name = "Warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Nonnull
    @PostMapping
    @Timed(value = "warehouse.create.time", description = "time to create a new warehouse", percentiles = {0.5, 0.9})
    protected ResponseEntity<Warehouse> create(@Valid @RequestBody WarehouseDto warehouseDto) {
        Optional<Warehouse> warehouse = warehouseService.createWarehouse(warehouseDto);
        return ResponseEntity.ok().body(warehouse.orElseThrow(NotFoundException::new));
    }

    @Nonnull
    @GetMapping
    @Timed(value = "warehouse.read.time", description = "time to read a new warehouse", percentiles = {0.5, 0.9})
    protected ResponseEntity<Warehouse> read(@RequestParam UUID uuid) {
        return ResponseEntity.ok().body(warehouseService.readWarehouse(uuid).orElseThrow(NotFoundException::new));
    }

    @Nonnull
    @PutMapping
    @Timed(value = "warehouse.update.time", description = "time to update a new warehouse", percentiles = {0.5, 0.9})
    protected ResponseEntity<Warehouse> update(@Valid @RequestBody WarehouseDto warehouseDto) {
        Optional<Warehouse> warehouse = warehouseService.updateWarehouse(warehouseDto);
        return ResponseEntity.ok().body(warehouse.orElseThrow(NotFoundException::new));
    }


    @Nonnull
    @DeleteMapping
    @Timed(value = "warehouse.delete.time", description = "time to delete a warehouse", percentiles = {0.5, 0.9})
    protected ResponseEntity<String> delete(@RequestParam UUID uuid) {
        Boolean deleted = warehouseService.deleteWarehouse(uuid);
        if (!deleted) throw new NotFoundException(uuid.toString());
        return ResponseEntity.ok().body(String.format("Your Warehouse with uuid %s has been deleted", uuid.toString()));
    }
}
