package com.zee.reporting.exception;


import com.zee.reporting.datatype.MessageType;
import com.zee.reporting.dto.ApiResponse;
import io.quarkus.security.AuthenticationFailedException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

/**
 * @dev : Ezekiel Eromosei
 * @date : 30 Sep, 2026
 */

@Slf4j
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFailedExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {
    @Override
    public Response toResponse(AuthenticationFailedException exception) {

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .successful(false)
                .messageType(MessageType.ERROR)
                .errorMessage("Authorization Failed")
                .build();
        log.error("Authorization Failed", exception);

        return  Response.status(Response.Status.UNAUTHORIZED).entity(apiResponse).build();
    }
}
