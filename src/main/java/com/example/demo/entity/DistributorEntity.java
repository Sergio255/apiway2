package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "distributor")
public class DistributorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distributor_id")
    private Long distributorId;

    @Column(name = "distributor_id_value", unique = true, length = 14)
    private String distributorIdValue;

    @Column(name = "energy_provider", length = 255)
    private String energyProvider;
}
