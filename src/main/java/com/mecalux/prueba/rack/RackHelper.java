package com.mecalux.prueba.rack;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.base.Racks;
import com.mecalux.prueba.common.exception.BadRequestException;
import com.mecalux.prueba.common.validate.RackValidation;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Component
@Validated
public class RackHelper {

    private final RackRepository rackRepository;
    private final RackConverter rackConverter;

    private final RackValidation rackValidation;

    public RackHelper(RackRepository rackRepository, RackConverter rackConverter, RackValidation rackValidation) {
        this.rackRepository = rackRepository;
        this.rackConverter = rackConverter;
        this.rackValidation = rackValidation;
    }

    protected Rack create(@NotNull Rack rack) {
        return rackRepository.save(rack);
    }

    public Rack read(@NotNull UUID uuid) {
        return rackRepository.getRackByUuid(uuid);
    }

    public Rack update(@NotNull Rack rack, @NotNull Integer id) {
        return rackRepository.save(addIdIntoRack(rack, id));
    }

    public Boolean delete(@NotNull UUID uuid) {
        return rackRepository.deleteRackByUuid(uuid) > 0;
    }

    protected Rack convertDtoToEntity(RackDto rackDto) {
        return rackConverter.convertDtoToEntity(rackDto);
    }

    protected RackDto generateUuid(RackDto rackDto) {
        rackDto.setUuid(UUID.randomUUID());
        return rackDto;
    }

    protected void validateNotExistUuid(RackDto rackDto) {
        if (rackDto.getUuid() != null) {
            throw new BadRequestException("The uuid should be null in the create operation");
        }
    }

    protected void validateRack(Integer allowedSize, List<Racks> racks, Family family) {
        rackValidation.validateRacksSize(racks.size() + 1, allowedSize);
        rackValidation.validateRacksFamily(racks, family);
    }

    protected Integer findIdByUuid(@NotNull UUID uuid) {
        Rack rackByUuid = rackRepository.getRackByUuid(uuid);
        if (rackByUuid == null) {
            throw new BadRequestException(String.format("The uuid %s don't exist in our system", uuid));
        }
        return rackByUuid.getId();
    }

    private Rack addIdIntoRack(Rack rack, Integer id) {
        rack.setId(id);
        return rack;
    }


}


