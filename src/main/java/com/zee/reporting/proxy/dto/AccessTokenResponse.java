package com.zee.reporting.proxy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccessTokenResponse(
   int expiresIn,
   String accessToken,
   String tokenType
){}