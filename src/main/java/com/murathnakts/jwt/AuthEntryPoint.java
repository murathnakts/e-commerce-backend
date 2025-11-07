package com.murathnakts.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murathnakts.handler.ApiResponse;
import com.murathnakts.handler.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        ApiResponse<?> errorResponse = ApiResponse.failure(
                ResponseMessage.TOKEN_INVALID.name(),
                ResponseMessage.TOKEN_INVALID.getMessage()
        );

        response.setStatus(ResponseMessage.TOKEN_INVALID.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
