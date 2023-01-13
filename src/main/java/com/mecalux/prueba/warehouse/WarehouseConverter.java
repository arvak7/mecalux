package com.mecalux.prueba.warehouse;

import com.mecalux.prueba.common.base.AbstractConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;


@Component
public class WarehouseConverter extends AbstractConverter<Warehouse, WarehouseDto> {

    public WarehouseConverter(DozerBeanMapper mapper) {
        super(mapper, Warehouse.class, WarehouseDto.class);
    }

}
