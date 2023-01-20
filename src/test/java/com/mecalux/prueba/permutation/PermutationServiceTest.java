package com.mecalux.prueba.permutation;

import com.mecalux.prueba.common.base.Family;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PermutationServiceTest {

    private PermutationService permutationService;

    @BeforeEach
    void init() {
        permutationService = new PermutationServiceImpl();
    }

    @Test
    void testNumberOfElementsMatch() {
        Family family = Family.ROB;
        int size = permutationService.calculatePermutationsByFamily(family).size();
        assertThat(size).isEqualTo(calcFactorial(family.getRacks().size()));
    }

    @Test
    void testElementsMatch() {
        Family family = Family.EST;
        Set<String> permutations = permutationService.calculatePermutationsByFamily(family);
        Set<String> expectedPermutations = createSampleExpectedPermutations();
        assertThat(Stream.of(permutations, expectedPermutations).flatMap(Set::stream).distinct().anyMatch(expectedPermutations::contains)).isTrue();
    }

    private Set<String> createSampleExpectedPermutations() {
        return new HashSet<>(Arrays.asList("ABC","ACB","BAC","BCA","CAB","CBA"));
    }

    private int calcFactorial(int num) {
        int factorial=1;
        for (int i = num; i > 0; i--) {
            factorial = factorial * i;
        }
        return factorial;
    }

}