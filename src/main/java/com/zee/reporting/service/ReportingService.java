package com.zee.reporting.service;


import com.aayushatharva.brotli4j.common.annotations.Local;
import com.zee.reporting.dto.PaymentDto;
import com.zee.reporting.entity.Payment;
import com.zee.reporting.repository.ReportRepository;
import com.zee.reporting.util.MapperUtil;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * @dev : Ezekiel Eromosei
 * @date : 30 Sep, 2026
 */

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ReportingService {

    private final ReportRepository reportRepository;
    private final MapperUtil mapperUtil;

    public List<PaymentDto> fetchPaymentsInDatRange(LocalDate startDate, LocalDate endDate){
        if(startDate == null){
            startDate = LocalDate.now(ZoneId.systemDefault());
        }

        if(startDate.isAfter(endDate)){
            startDate  = LocalDate.now(ZoneId.systemDefault()).minusDays(7);
            endDate  = LocalDate.now(ZoneId.systemDefault()).plusDays(1);
        }

        List<Payment> payments = reportRepository.paymentsBetween(
                startDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                endDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return payments.stream()
                .peek(payment -> log.info("payment received:{}", payment.toString()))
                .map(mapperUtil::toPaymentDto)
                .toList();
    }

    public List<PaymentDto> getPaginatedPayments(int size){
        if(size < 1) size = 1;
        List<Payment> payments = reportRepository.page(size);

        return payments.stream()
                .peek(payment -> log.info("payment pagination received:{}", payment.toString()))
                .map(mapperUtil::toPaymentDto)
                .toList();
    }
}
