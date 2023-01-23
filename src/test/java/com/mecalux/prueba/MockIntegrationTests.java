package com.mecalux.prueba;

import com.google.gson.Gson;
import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.common.base.Racks;
import com.mecalux.prueba.warehouse.Warehouse;
import com.mecalux.prueba.warehouse.WarehouseDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class MockIntegrationTests {

    private static final String CLIENT_TEST_1 = "clientTest1";
    private static final List<String> CORRECT_RACKS_FOR_ROB_FAMILY = Arrays.asList(Racks.A.toString(), Racks.D.toString(), Racks.C.toString());
    private static final String WAREHOUSE_ENDPOINT = "/api/v1/warehouses";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void warehouseCrudTest() throws Exception {
        Gson gson = new Gson();

        // Create
        MvcResult resultCreate = mockMvc.perform(post(WAREHOUSE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(WarehouseDto.builder()
                                .family(Family.ROB.toString())
                                .client(CLIENT_TEST_1)
                                .size(3)
                                .racks(CORRECT_RACKS_FOR_ROB_FAMILY)
                                .build())))
                .andExpect(status().isOk())
                .andReturn();
        Warehouse warehouseCreated = gson.fromJson(resultCreate.getResponse().getContentAsString(), Warehouse.class);
        assertThat(warehouseCreated).isNotNull();
        assertThat(warehouseCreated.getId()).isNotNull();
        assertThat(warehouseCreated.getUuid()).isNotNull();
        assertThat(warehouseCreated.getRacks()).isNotEmpty();

        // Read
        ResultActions resultRead = mockMvc.perform(get(WAREHOUSE_ENDPOINT.concat("/").concat(warehouseCreated.getUuid().toString()))
                .contentType(MediaType.APPLICATION_JSON));
//                .param("uuid", warehouseCreated.getUuid().toString()));
        resultRead.andExpect(status().isOk());
        resultRead.andExpect(MockMvcResultMatchers.jsonPath("$.client").value(CLIENT_TEST_1));

        // Update
        mockMvc.perform(put(WAREHOUSE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(WarehouseDto.builder()
                                .family(Family.ROB.toString())
                                .uuid(warehouseCreated.getUuid())
                                .client(CLIENT_TEST_1)
                                .size(5)
                                .racks(CORRECT_RACKS_FOR_ROB_FAMILY)
                                .build())))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(5));

        // Delete
        mockMvc.perform(delete(WAREHOUSE_ENDPOINT.concat("/").concat(warehouseCreated.getUuid().toString()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(String.format("Your Warehouse with uuid %s has been deleted", warehouseCreated.getUuid().toString())));

    }
}
