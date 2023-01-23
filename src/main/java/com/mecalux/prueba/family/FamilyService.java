package com.mecalux.prueba.family;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.validate.family.ValidateFamily;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface FamilyService {
    Family[] getAllFamilies();
    List<String> getAllRacksByFamily(@RequestParam @ValidateFamily Family family);
}
