package com.mecalux.prueba.permutation;

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
@RequestMapping("/api/v1/permutations")
@Tag(name = "Permutation")
@RequiredArgsConstructor
public class PermutationController {

    private final PermutationService permutationService;

    @Nonnull
    @GetMapping("/{family}/{k}")
    @Timed(value = "permutations.read.time", description = "time to read a new permutation", percentiles = {0.5, 0.9})
    protected ResponseEntity<List<String>> calcPermutations(@PathVariable @ValidateFamily Family family, @PathVariable int k) {
        return ResponseEntity.ok().body(permutationService.calculatePermutationsByFamily(family, k));
    }

}
