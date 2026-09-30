package com.zee.reporting.dto;


import com.zee.reporting.datatype.Mode;
import com.zee.reporting.datatype.PaymentStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @dev : Ezekiel Eromosei
 * @date : 30 Sep, 2026
 */


@Builder
public record PaymentDto(
        String transactionReference,
        BigDecimal price,
        Mode mode,
        PaymentStatus status,
        Instant createdTimestamp
) {
}
