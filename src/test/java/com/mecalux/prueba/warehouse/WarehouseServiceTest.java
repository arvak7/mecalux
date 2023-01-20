package com.mecalux.prueba.warehouse;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.base.Racks;
import com.mecalux.prueba.common.exception.BadRequestException;
import com.mecalux.prueba.common.validate.RackValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {

    private static final String CLIENT_TEST_1 = "clientTest1";
    private static final List<String> WRONG_RACKS_FOR_ROB_FAMILY = Arrays.asList(Racks.A.toString(), Racks.B.toString(), Racks.C.toString());
    private static final List<String> CORRECT_RACKS_FOR_ROB_FAMILY = Arrays.asList(Racks.A.toString(), Racks.D.toString(), Racks.C.toString());

    private WarehouseService warehouseService;
    private WarehouseHelper warehouseHelper;
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private WarehouseConverter warehouseConverter;
    private RackValidation rackValidation;

    @BeforeEach
    void init() {
        rackValidation = new RackValidation();
        warehouseHelper = new WarehouseHelper(warehouseRepository, warehouseConverter, rackValidation);
        warehouseService = new WarehouseServiceImpl(warehouseHelper);
    }

    @Test
    void createWarehouseWithValidatedRacksFamily() {
        Warehouse warehouse = Warehouse.builder().family(Family.ROB).client(CLIENT_TEST_1).size(3).build();
        WarehouseDto warehouseDto = WarehouseDto.builder().family(Family.ROB.toString()).client(CLIENT_TEST_1).size(3).racks(CORRECT_RACKS_FOR_ROB_FAMILY).build();

        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);
        when(warehouseConverter.convertDtoToEntity(any(WarehouseDto.class))).thenReturn(warehouse);
        Optional<Warehouse> savedWarehouse = warehouseService.createWarehouse(warehouseDto);
        assertThat(savedWarehouse).isPresent();
        assertThat(savedWarehouse.get().getFamily()).isEqualTo(Family.ROB);
    }

    @Test
    void testInvalidRackByFamily() {
        Warehouse warehouse = Warehouse.builder().family(Family.ROB).client(CLIENT_TEST_1).size(3).build();
        WarehouseDto warehouseDto = WarehouseDto.builder().family(Family.ROB.toString()).client(CLIENT_TEST_1).size(3).racks(WRONG_RACKS_FOR_ROB_FAMILY).build();

        when(warehouseConverter.convertDtoToEntity(any(WarehouseDto.class))).thenReturn(warehouse);
        assertThatThrownBy(() -> {
            warehouseService.createWarehouse(warehouseDto);
        }).isInstanceOf(BadRequestException.class);
    }

    @Test
    void testInvalidRackBySize() {
        Warehouse warehouse = Warehouse.builder().family(Family.ROB).client(CLIENT_TEST_1).size(1).build();
        WarehouseDto warehouseDto = WarehouseDto.builder().family(Family.ROB.toString()).client(CLIENT_TEST_1).size(1).racks(CORRECT_RACKS_FOR_ROB_FAMILY).build();

        when(warehouseConverter.convertDtoToEntity(any(WarehouseDto.class))).thenReturn(warehouse);
        assertThatThrownBy(() -> {
            warehouseService.createWarehouse(warehouseDto);
        }).isInstanceOf(BadRequestException.class);
    }
}