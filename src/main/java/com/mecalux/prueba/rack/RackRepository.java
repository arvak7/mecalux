package com.mecalux.prueba.rack;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RackRepository extends JpaRepository<Rack, Integer> {
    Rack getRackByUuid(UUID uuid);

    Integer deleteRackByUuid(UUID uuid);

}
