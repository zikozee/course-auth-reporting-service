package com.zee.reporting.entity;


import com.zee.reporting.datatype.Mode;
import com.zee.reporting.datatype.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @dev : Ezekiel Eromosei
 * @date : 29 Sep, 2026
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_reference")
    private String transactionReference;
    private BigDecimal price;
    private String initiator;// user from auth server

    @Enumerated(EnumType.STRING)
    private Mode mode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "created_at")
    private Instant createdAt;
}
