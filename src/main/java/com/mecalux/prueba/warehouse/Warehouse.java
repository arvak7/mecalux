package com.mecalux.prueba.warehouse;

import com.mecalux.prueba.common.base.Family;
import com.mecalux.prueba.rack.Rack;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;

@Data
@Entity
@Builder(toBuilder = true)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ParametersAreNonnullByDefault
@Table(name = "WAREHOUSES", schema = "PUBLIC", catalog = "DEMO")
public class Warehouse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "UUID")
    private UUID uuid;
    @Basic
    @Column(name = "CLIENT")
    private String client;
    @Basic
    @Column(name = "FAMILY")
    private Family family;
    @Basic
    @Column(name = "SIZE")
    private Integer size;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    @JoinColumn(name = "warehouse_id")
    private List<Rack> racks;


}
