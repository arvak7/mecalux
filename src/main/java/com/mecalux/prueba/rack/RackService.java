package com.mecalux.prueba.rack;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface RackService {
    Optional<Rack> createRack(RackDto rack);
    Optional<Rack> readRack(UUID uuid);
    Optional<Rack> updateRack(RackDto rack);
    Boolean deleteRack(UUID uuid);
}
