package com.zee.reporting.controller;


import com.zee.reporting.datatype.MessageType;
import com.zee.reporting.dto.ApiResponse;
import com.zee.reporting.dto.PaymentDto;
import com.zee.reporting.permission.Permission;
import com.zee.reporting.proxy.PaymentClientUtil;
import com.zee.reporting.proxy.dto.UserTransactionResponse;
import com.zee.reporting.service.ReportingService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @dev : Ezekiel Eromosei
 * @date : 30 Sep, 2026
 */

@Path("payment")
@RequiredArgsConstructor
@Produces(value = MediaType.APPLICATION_JSON)
public class ReportingController {

    private final ReportingService reportingService;
    private final PaymentClientUtil paymentClientUtil;

    @GET
    @Path("range")
    @RolesAllowed(value = Permission.VIEW_PAYMENT)
    public Response getPaymentsInRange(@QueryParam("startDate")LocalDate startDate,
                                       @QueryParam("endDate")LocalDate endDate) {

        return Response.ok(ApiResponse.<List<PaymentDto>>builder()
                        .successful(true)
                        .messageType(MessageType.SUCCESS)
                        .data(reportingService.fetchPaymentsInDatRange(startDate, endDate))
                        .build()
                )
                .build();

    }

    @GET
    @Path("page")
    @RolesAllowed(value = Permission.VIEW_PAYMENT)
    public Response getPaymentsInRange(@QueryParam("pageSize")int pageSize) {

        return Response.ok(ApiResponse.<List<PaymentDto>>builder()
                        .successful(true)
                        .messageType(MessageType.SUCCESS)
                        .data(reportingService.getPaginatedPayments(pageSize))
                        .build()
                )
                .build();

    }


    @GET
    @Path("transactions")
    @RolesAllowed(value = Permission.VIEW_PAYMENT)
    public Response getPaymentsTransactions(@QueryParam("startDate")LocalDate startDate) {

        return Response.ok(ApiResponse.<UserTransactionResponse>builder()
                        .successful(true)
                        .messageType(MessageType.SUCCESS)
                        .data(paymentClientUtil.getUserTransactions(startDate))
                        .build()
                )
                .build();

    }
}
