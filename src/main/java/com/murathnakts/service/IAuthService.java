package com.murathnakts.service;

import com.murathnakts.dto.*;

public interface IAuthService {
    DtoUser register(DtoUserIU dtoUserIU);
    DtoLogin login(DtoLoginIU request);
    DtoRefreshToken refreshToken(DtoRefreshTokenIU request);
}
