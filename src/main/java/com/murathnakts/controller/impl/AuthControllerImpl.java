package com.murathnakts.controller.impl;

import com.murathnakts.controller.IAuthController;
import com.murathnakts.dto.*;
import com.murathnakts.handler.ApiResponse;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.service.IAuthService;
import jakarta.validation.Valid;
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
    public ApiResponse<DtoLogin> login(@Valid @RequestBody DtoLoginIU dtoLoginIU) {
        return ApiResponse.success(authService.login(dtoLoginIU), ResponseMessage.LOGIN_SUCCESS);
    }

    @PostMapping("/refresh-token")
    @Override
    public ApiResponse<DtoRefreshToken> refreshToken(@Valid @RequestBody DtoRefreshTokenIU dtoRefreshTokenIU) {
        return ApiResponse.success(authService.refreshToken(dtoRefreshTokenIU), ResponseMessage.TOKEN_REFRESHED);
    }

    @PostMapping("/send-otp")
    @Override
    public ApiResponse<Boolean> sendOtp(@Valid @RequestBody DtoSendOtpIU dtoSendOtpIU) {
        return ApiResponse.success(authService.sendOtp(dtoSendOtpIU), ResponseMessage.OTP_SEND);
    }

    @PostMapping("/verify-otp")
    @Override
    public ApiResponse<DtoVerifyOtp> verifyOtp(@Valid @RequestBody DtoVerifyOtpIU dtoVerifyOtpIU) {
        return ApiResponse.success(authService.verifyOtp(dtoVerifyOtpIU), ResponseMessage.OTP_VERIFY);
    }

    @PostMapping("/reset-password")
    @Override
    public ApiResponse<Boolean> resetPassword(@Valid @RequestBody DtoResetPasswordIU dtoResetPasswordIU) {
        return ApiResponse.success(authService.resetPassword(dtoResetPasswordIU), ResponseMessage.PASSWORD_RESET);
    }
}
