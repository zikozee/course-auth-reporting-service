package com.zee.reporting.proxy;


import com.zee.reporting.dto.ApiResponse;
import com.zee.reporting.proxy.dto.AccessTokenResponse;
import com.zee.reporting.proxy.dto.UserTransactionResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.Base64;

/**
 * @dev : Ezekiel Eromosei
 * @date : 03 Oct, 2026
 */

@Slf4j
@ApplicationScoped
public class AuthClientUtil {

    @RestClient
    @Inject
    private AuthClient authClient;

    @ConfigProperty(name = "payment.auth.client-id")
    String clientId;
    @ConfigProperty(name = "payment.auth.client-secret")
    String clientSecret;

    public AccessTokenResponse getAccessToken() {

        final String authHeader = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        try(RestResponse<ApiResponse<AccessTokenResponse>> accessTokenResponse =
                    authClient.getAccessTokenResponse(authHeader, "client_credentials", "openid")) {
            if (accessTokenResponse.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException("Error getting access token");
            }
            return accessTokenResponse.getEntity().getData();
        }catch (Exception e){
            throw new RuntimeException("Error getting access token");
        }
    }
}
