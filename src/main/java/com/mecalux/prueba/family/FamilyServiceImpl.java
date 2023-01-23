package com.mecalux.prueba.family;

import com.mecalux.prueba.common.base.Family;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Override
    public Family[] getAllFamilies() {
        return Family.values();
    }

    @Override
    public List<String> getAllRacksByFamily(Family family) {
        return family.getRacks().stream().map(Enum::toString).collect(Collectors.toList());
    }
}
