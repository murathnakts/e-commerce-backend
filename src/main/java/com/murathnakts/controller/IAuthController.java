package com.murathnakts.controller;

import com.murathnakts.dto.*;
import com.murathnakts.handler.ApiResponse;

public interface IAuthController {
    ApiResponse<DtoUser> register(DtoUserIU dtoUserIU);
    ApiResponse<DtoLogin> login(DtoLoginIU dtoLoginIU);
    ApiResponse<DtoRefreshToken> refreshToken(DtoRefreshTokenIU dtoRefreshTokenIU);
    ApiResponse<Boolean> sendOtp(DtoSendOtpIU dtoSendOtpIU);
    ApiResponse<DtoVerifyOtp> verifyOtp(DtoVerifyOtpIU dtoVerifyOtpIU);
    ApiResponse<Boolean> resetPassword(DtoResetPasswordIU dtoResetPasswordIU);
}
