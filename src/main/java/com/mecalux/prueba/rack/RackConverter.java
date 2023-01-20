package com.mecalux.prueba.rack;

import com.mecalux.prueba.common.base.AbstractConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class RackConverter extends AbstractConverter<Rack, RackDto> {
    public RackConverter(DozerBeanMapper mapper) {
        super(mapper, Rack.class, RackDto.class);
    }
}
