package com.mecalux.prueba.family;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.validate.family.ValidateFamily;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/families")
@Tag(name = "Family")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;

    @Nonnull
    @GetMapping
    @Timed(value = "families.read.time", description = "time to read a family", percentiles = {0.5, 0.9})
    protected ResponseEntity<Family[]> getAllFamilies() {
        return ResponseEntity.ok().body(familyService.getAllFamilies());
    }

    @Nonnull
    @GetMapping("/{family}/racks")
    @Timed(value = "rack.by.family.read.time", description = "time to read the racks by family", percentiles = {0.5, 0.9})
    protected ResponseEntity<List<String>> getAllFamilies(@PathVariable @ValidateFamily Family family) {
        return ResponseEntity.ok().body(familyService.getAllRacksByFamily(family));
    }

}
