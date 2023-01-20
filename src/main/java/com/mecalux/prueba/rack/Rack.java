package com.mecalux.prueba.rack;

import com.mecalux.prueba.common.base.Racks;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;

@Data
@Entity
@EqualsAndHashCode
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ParametersAreNonnullByDefault
@Table(name = "RACKS", schema = "PUBLIC", catalog = "DEMO")
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "UUID")
    private UUID uuid;
    @Basic
    @Column(name = "TYPE")
    private Racks type;
    @Basic
    @Column(name = "WAREHOUSE_ID")
    private Integer warehouse_id;


}
