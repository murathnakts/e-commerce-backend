package com.murathnakts.service.impl;

import com.murathnakts.entity.RefreshToken;
import com.murathnakts.entity.Users;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.repository.RefreshTokenRepository;
import com.murathnakts.service.IRefreshTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken findRefreshTokenOrThrow(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new BaseException(ResponseMessage.TOKEN_INVALID));
    }

    @Override
    public boolean validateRefreshToken(LocalDateTime expiredDate) {
        return LocalDateTime.now().isBefore(expiredDate);
    }

    @Override
    @Transactional
    public RefreshToken createRefreshToken(Users user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setExpiredDate(LocalDateTime.now().plusHours(4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshTokenRepository.save(refreshToken);
    }
}
