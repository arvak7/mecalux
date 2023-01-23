package com.mecalux.prueba.permutation;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.base.Racks;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermutationServiceImpl implements PermutationService {

    @Override
    public List<String> calculatePermutationsByFamily(Family family, int k) {
        List<String> permutations = new ArrayList<>();

        List<Racks> racksList = family.getRacks();
        char[] elements = new char[racksList.size()];
        for (int i = 0; i < racksList.size(); i++) {
            elements[i] = racksList.get(i).name().charAt(0);
        }

        permuteWithRepetition(elements, "", elements.length, k, permutations);
        return permutations;
    }

    private static void permuteWithRepetition(char[] elements, String prefix, int n, int k, List<String> permutations) {
        if (k == 0) {
            permutations.add(prefix);
            return;
        }

        for (int i = 0; i < n; i++) {
            permuteWithRepetition(elements, prefix + elements[i], n, k - 1, permutations);
        }
    }
}



