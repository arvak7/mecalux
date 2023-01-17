package com.mecalux.prueba.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse getWarehouseByUuid(UUID uuid);

    Integer deleteWarehouseById(Integer id);
}
