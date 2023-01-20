package com.mecalux.prueba.permutation;

import com.mecalux.prueba.common.base.Family;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PermutationService {

    Set<String> calculatePermutationsByFamily(Family family);

}
