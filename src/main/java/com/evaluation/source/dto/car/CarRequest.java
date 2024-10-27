package com.evaluation.source.dto.car;

import java.util.List;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest{
    private Long carId;
    private Long carInfoId;
    private Integer modelId;
    private String version;
    private String color;
    private Integer year;
    private Double price;
    private Integer mileAge;
    private String plateNumber;
    private List<String> images;
    private String desc;
    private String carOwnerId;
    private String stauts;
    private String kmDriven;
    public List<Integer> repairAreas;
}
