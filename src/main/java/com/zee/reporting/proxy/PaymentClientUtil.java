package com.zee.reporting.proxy;


import com.zee.reporting.dto.ApiResponse;
import com.zee.reporting.proxy.dto.AccessTokenResponse;
import com.zee.reporting.proxy.dto.UserTransactionResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;

import java.time.LocalDate;

/**
 * @dev : Ezekiel Eromosei
 * @date : 02 Oct, 2026
 */

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class PaymentClientUtil {

    @RestClient
    @Inject
    PaymentClient paymentClient;

    private final AuthClientUtil authClientUtil;

    public UserTransactionResponse getUserTransactions(LocalDate startDate) {

        AccessTokenResponse token = authClientUtil.getAccessToken();

        final String bearerToken = "Bearer " + token.accessToken();

        try (RestResponse<ApiResponse<UserTransactionResponse>> userTransactionsResponse =
                     paymentClient.getUserTransactions(bearerToken, startDate)) {

            if (userTransactionsResponse.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException("Unable to fetch user transactions");
            }
            return userTransactionsResponse.getEntity().getData();

        } catch (RuntimeException e) {
            log.error("Unable to fetch user transactions");
            throw new RuntimeException("Unable to fetch user transactions");
        }
    }


}
