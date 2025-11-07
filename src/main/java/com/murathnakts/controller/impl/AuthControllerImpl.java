package com.murathnakts.controller.impl;

import com.murathnakts.controller.IAuthController;
import com.murathnakts.dto.*;
import com.murathnakts.handler.ApiResponse;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements IAuthController {

    private final IAuthService authService;

    public AuthControllerImpl(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Override
    public ApiResponse<DtoUser> register(@Valid @RequestBody DtoUserIU dtoUserIU) {
        return ApiResponse.success(authService.register(dtoUserIU), ResponseMessage.USER_CREATED);
    }

    @PostMapping("/login")
    @Override
    public ApiResponse<DtoLogin> login(@Valid @RequestBody DtoLoginIU request) {
        return ApiResponse.success(authService.login(request), ResponseMessage.LOGIN_SUCCESS);
    }

    @PostMapping("/refresh-token")
    @Override
    public ApiResponse<DtoRefreshToken> refreshToken(@Valid @RequestBody DtoRefreshTokenIU request) {
        return ApiResponse.success(authService.refreshToken(request), ResponseMessage.TOKEN_REFRESHED);
    }
}
