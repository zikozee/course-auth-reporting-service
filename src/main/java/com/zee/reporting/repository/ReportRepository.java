package com.zee.reporting.repository;


import com.zee.reporting.entity.Payment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.List;

/**
 * @dev : Ezekiel Eromosei
 * @date : 29 Sep, 2026
 */

@ApplicationScoped
public class ReportRepository implements PanacheRepository<Payment> {

    public List<Payment> paymentsBetween(Instant startDate, Instant endDate) {
        return list("createdAt >= ?1 and createdAt <= ?2", startDate, endDate);
    }

    public List<Payment> page(int pageSize) {
        return findAll(Sort.ascending("id")).page(Page.ofSize(pageSize)).list();
    }
}
