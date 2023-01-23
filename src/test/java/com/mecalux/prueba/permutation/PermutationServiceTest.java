package com.mecalux.prueba.permutation;

import com.mecalux.prueba.common.base.Family;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
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
        int size = permutationService.calculatePermutationsByFamily(family, 3).size();
        assertThat(size).isEqualTo(calcPow(family.getRacks().size(), 3));
    }

    @Test
    void testElementsMatch() {
        Family family = Family.EST;
        List<String> permutations = permutationService.calculatePermutationsByFamily(family, 3);
        List<String> expectedPermutations = createSampleExpectedPermutations();
        assertThat(Stream.of(permutations, expectedPermutations).flatMap(List::stream).distinct().anyMatch(expectedPermutations::contains)).isTrue();
    }

    private List<String> createSampleExpectedPermutations() {
        return Arrays.asList("AAA","AAB","AAC","ABA","ABB","ABC","ACA","ACB","ACC","BAA","BAB","BAC","BBA","BBB","BBC","BCA","BCB","BCC","CAA","CAB","CAC","CBA","CBB","CBC","CCA","CCB","CCC");
    }

    private int calcPow(int n, int k) {
        return (int) Math.pow(n, k);
    }

}