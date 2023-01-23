package com.mecalux.prueba.rack;

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
@RequestMapping("/api/v1/racks")
@Tag(name = "rack")
@RequiredArgsConstructor
public class RackController {

    private final RackService rackService;

    @Nonnull
    @PostMapping
    @Timed(value = "rack.create.time", description = "time to create a new rack", percentiles = {0.5, 0.9})
    protected ResponseEntity<Rack> create(@Valid @RequestBody RackDto rackDto) {
        Optional<Rack> rack = rackService.createRack(rackDto);
        return ResponseEntity.ok().body(rack.orElseThrow(NotFoundException::new));
    }

    @Nonnull
    @GetMapping
    @Timed(value = "rack.read.time", description = "time to read a new rack", percentiles = {0.5, 0.9})
    protected ResponseEntity<Rack> read(@RequestParam UUID uuid) {
        return ResponseEntity.ok().body(rackService.readRack(uuid).orElseThrow(NotFoundException::new));
    }

    @Nonnull
    @PutMapping
    @Timed(value = "rack.update.time", description = "time to update a new rack", percentiles = {0.5, 0.9})
    protected ResponseEntity<Rack> update(@Valid @RequestBody RackDto rackDto) {
        Optional<Rack> rack = rackService.updateRack(rackDto);
        return ResponseEntity.ok().body(rack.orElseThrow(NotFoundException::new));
    }


    @Nonnull
    @DeleteMapping
    @Timed(value = "rack.delete.time", description = "time to delete a rack", percentiles = {0.5, 0.9})
    protected ResponseEntity<String> delete(@RequestParam UUID uuid) {
        boolean deleted = rackService.deleteRack(uuid);
        if (!deleted) throw new NotFoundException(uuid.toString());
        return ResponseEntity.ok().body(String.format("Your rack with uuid %s has been deleted", uuid.toString()));
    }

}
