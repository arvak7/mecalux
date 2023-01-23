package com.mecalux.prueba.permutation;

import com.mecalux.prueba.common.base.Family;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermutationService {

    List<String> calculatePermutationsByFamily(Family family, int k);
}
