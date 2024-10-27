package com.evaluation.source.model;

import java.time.Instant;

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
@Table(name = "payment")
public class Transaction {
    public enum TransactionStatus {
        PENDING,
        COMPLETED,
        FAILED
    }

    public enum Method {
        BANK,
        CASH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_info_id")
    private CarInfo carId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer")
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller")
    private User seller;

    @Column(name = "method")
    @Enumerated(EnumType.STRING)
    private Method method;

    @Column(name = "amount")
    private double amount;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "transaction_desc", length = Integer.MAX_VALUE)
    private String transactionDesc;

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}
