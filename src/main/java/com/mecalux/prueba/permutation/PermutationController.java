package com.mecalux.prueba.permutation;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.validate.family.ValidateFamily;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/permutation")
@Tag(name = "Permutation")
@RequiredArgsConstructor
public class PermutationController {

    private final PermutationService permutationService;

    @Nonnull
    @GetMapping
    @Timed(value = "permutations.read.time", description = "time to read a new permutation", percentiles = {0.5, 0.9})
    protected ResponseEntity<Set<String>> calcPermutations(@RequestParam @ValidateFamily Family family) {
        return ResponseEntity.ok().body(permutationService.calculatePermutationsByFamily(family));
    }

}
