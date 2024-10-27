package com.evaluation.source.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Entity
@Getter
@Setter
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private Long carId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Model model;

    @Column(name = "version")
    private String version;

    @Column(name = "year")
    private Integer year;

    @Column(name = "color")
    private String color;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    private Set<CarInfo> carInfo;
}
