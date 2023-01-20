package com.mecalux.prueba.warehouse;

import com.mecalux.prueba.common.validate.family.ValidateFamily;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WarehouseDto {
    private UUID uuid;
    @Size(max = 50, message = "{validation.size.too_long}")
    @NotNull
    private String client;
    @NotNull
    @ValidateFamily
    private String family;
    @Min(value = 0, message = "{validation.size.min}")
    @Max(value = 99, message = "{validation.size.max}")
    @NotNull
    private Integer size;
    private List<String> racks;
}
