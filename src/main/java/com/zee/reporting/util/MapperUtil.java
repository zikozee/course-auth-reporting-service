package com.zee.reporting.util;


import com.zee.reporting.dto.PaymentDto;
import com.zee.reporting.entity.Payment;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @dev : Ezekiel Eromosei
 * @date : 30 Sep, 2026
 */

@ApplicationScoped
public class MapperUtil {

    public PaymentDto toPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .transactionReference(payment.getTransactionReference())
                .price(payment.getPrice())
                .mode(payment.getMode())
                .status(payment.getStatus())
                .createdTimestamp(payment.getCreatedAt())
                .build();
    }
}
