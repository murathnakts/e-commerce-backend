package com.murathnakts.service;

import com.murathnakts.entity.RefreshToken;
import com.murathnakts.entity.User;

import java.time.LocalDateTime;

public interface IRefreshTokenService {
    RefreshToken findByRefreshToken(String refreshToken);
    Boolean validateRefreshToken(LocalDateTime expiredDate);
    RefreshToken createRefreshToken(User user);
    void deleteRefreshToken(Long id);
}
