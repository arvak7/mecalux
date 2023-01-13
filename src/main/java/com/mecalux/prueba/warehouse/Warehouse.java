package com.mecalux.prueba.warehouse;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;

@Data
@Entity
@EqualsAndHashCode
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
    private String family;
    @Basic
    @Column(name = "SIZE")
    private Integer size;


}
