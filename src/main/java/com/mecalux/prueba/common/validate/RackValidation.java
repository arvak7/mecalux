package com.mecalux.prueba.common.validate;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.base.Racks;
import com.mecalux.prueba.common.exception.BadRequestException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class RackValidation {

    BiConsumer<List<Racks>, Family> validateRacksByFamily = (racks, family) -> racks.forEach(r -> {
        if (!family.getRacks().contains(r)) {
            throw new BadRequestException(String.format("The %s rack is not valid in the %s family. The valid are %s", r, family, family.getRacks()));
        }
    });

    BiConsumer<Integer, Integer> validateRacksSize = (rackSize, allowedSize) -> {
        if (rackSize > allowedSize) throw new BadRequestException(String.format("The rack size %d is greater than the maximum warehouse size %d", rackSize, allowedSize));
    };

    public void validateRacksSize(@NotNull Integer rackSize, @NotNull Integer allowedSize) {
        validateRacksSize.accept(rackSize, allowedSize);
    }

    public void validateRacksFamily(@NotNull List<Racks> racks, @NotNull Family family) {
        validateRacksByFamily.accept(racks, family);
    }

}
