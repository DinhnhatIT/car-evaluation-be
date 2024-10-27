package com.evaluation.source.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
@Table(name = "car_repair")
public class CarRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use IDENTITY for auto-incrementing
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_repair")
    private String nameRepair;

    @Column(name = "reduction_repair")
    private Double reductionRepair; 
}
