package com.evaluation.source.model;

import java.util.List;

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
@Table(name = "car_info")
public class CarInfo {

    public enum OrderType {
        BUY_ORDER,
        SELL_ORDER
    }
    public enum OrderStatus {
        T,
        F
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private Long carInfoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "mile_age")
    private Integer mileAge;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "price")
    private Double price;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String desc;

    @OneToMany(mappedBy = "carInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CarImage> images;

    @Column(name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
