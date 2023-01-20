package com.mecalux.prueba.rack;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RackDto {
    private UUID uuid;
    @NotNull
    private String type;
    @NotNull
    private Integer warehouse_id;
}
