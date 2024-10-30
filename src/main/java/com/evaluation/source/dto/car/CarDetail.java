package com.evaluation.source.dto.car;

import java.io.Serializable;
import java.util.List;

import lombok.*;

/**
 * DTO for {@link com.evaluation.source.model.CarInfo}
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDetail implements Serializable{
    private Long carId;
    private Long carInfoId;
    private String plateNumber;
    private String modelName;
    private String brandName;
    private String version;
    private String color;
    private Integer year;
    private Integer mileage;
    private Double price;
    private Double buyPrice;
    private List<String> images;
    private String carOwnerId;
    private String desc;
}
