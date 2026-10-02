package com.zee.reporting.proxy;


import com.zee.reporting.dto.ApiResponse;
import com.zee.reporting.proxy.dto.UserTransactionResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;

import java.time.LocalDate;

/**
 * @dev : Ezekiel Eromosei
 * @date : 02 Oct, 2026
 */

@RegisterRestClient(configKey = "payment-server")
public interface PaymentClient {

    @GET
    @Path("/system")
    @Produces(MediaType.APPLICATION_JSON)
    RestResponse<ApiResponse<UserTransactionResponse>> getUserTransactions(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                                                           @QueryParam("startDate")LocalDate startDate);
}
