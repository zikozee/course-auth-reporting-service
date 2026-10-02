package com.zee.reporting.proxy;


import com.zee.reporting.dto.ApiResponse;
import com.zee.reporting.proxy.dto.AccessTokenResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;

/**
 * @dev : Ezekiel Eromosei
 * @date : 03 Oct, 2026
 */

@RegisterRestClient(configKey = "auth-server")
public interface AuthClient {

    @POST
    @Path("/oauth2/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    RestResponse<ApiResponse<AccessTokenResponse>> getAccessTokenResponse(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization,
                                @FormParam("grant_type") String grantType,
                                @FormParam("scope") String scope);

}
