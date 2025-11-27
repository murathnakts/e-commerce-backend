package com.murathnakts.service;

import com.murathnakts.entity.RefreshToken;
import com.murathnakts.entity.Users;

import java.time.LocalDateTime;

public interface IRefreshTokenService {
    RefreshToken findByRefreshToken(String refreshToken);
    boolean validateRefreshToken(LocalDateTime expiredDate);
    RefreshToken createRefreshToken(Users user);
}
