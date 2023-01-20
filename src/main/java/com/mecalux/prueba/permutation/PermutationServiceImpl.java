package com.mecalux.prueba.permutation;

import com.mecalux.prueba.common.base.Family;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermutationServiceImpl implements PermutationService {

    @Override
    public Set<String> calculatePermutationsByFamily(Family family) {
        return findPermutations(family.getRacks().stream().map(Enum::toString).collect(Collectors.joining()));
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // Función recursiva para generar todas las permutaciones de una string
    private Set<String> permutations(char[] chars, int currentIndex, Set<String> permutations) {
        if (currentIndex == chars.length - 1) {
            permutations.add(String.valueOf(chars));
        }

        for (int i = currentIndex; i < chars.length; i++) {
            swap(chars, currentIndex, i);
            permutations(chars, currentIndex + 1, permutations);
            swap(chars, currentIndex, i);
        }

        return permutations;
    }

    private Set<String> findPermutations(String str) {
        // caso base
        Set<String> permutations = new HashSet<>();
        if (str == null || str.length() == 0) {
            return permutations;
        }

        return permutations(str.toCharArray(), 0, permutations);

    }

}
