package com.murathnakts.service;

import com.murathnakts.dto.*;

public interface IAuthService {
    DtoUser register(DtoUserIU dtoUserIU);
    DtoLogin login(DtoLoginIU dtoLoginIU);
    DtoRefreshToken refreshToken(DtoRefreshTokenIU dtoRefreshTokenIU);
    Boolean sendOtp(DtoSendOtpIU dtoSendOtpIU);
    DtoVerifyOtp verifyOtp(DtoVerifyOtpIU dtoVerifyOtpIU);
    Boolean resetPassword(DtoResetPasswordIU dtoResetPasswordIU);
}
