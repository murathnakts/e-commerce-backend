package com.murathnakts.service.impl;

import com.murathnakts.dto.*;
import com.murathnakts.entity.RefreshToken;
import com.murathnakts.entity.Users;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.service.IAuthService;
import com.murathnakts.service.IJwtService;
import com.murathnakts.service.IRefreshTokenService;
import com.murathnakts.service.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    private final IUserService userService;
    private final IRefreshTokenService refreshTokenService;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(IUserService userService,
                           AuthenticationManager authenticationManager,
                           IJwtService jwtService,
                           IRefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public DtoUser register(DtoUserIU dtoUserIU) {
        if (userService.isEmailTaken(dtoUserIU.getEmail())) {
            throw new BaseException(ResponseMessage.USER_ALREADY_REGISTERED);
        }
        return userService.createUser(dtoUserIU);
    }

    @Override
    public DtoLogin login(DtoLoginIU dtoLoginIU) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dtoLoginIU.getEmail(), dtoLoginIU.getPassword());
            authenticationManager.authenticate(authenticationToken);
            Users user = userService.findByEmail(dtoLoginIU.getEmail());
            String accessToken = jwtService.generateToken(user);
            RefreshToken savedRefreshToken = refreshTokenService.createRefreshToken(user);
            return new DtoLogin(user.getId(), accessToken, savedRefreshToken.getRefreshToken(), savedRefreshToken.getExpiredDate());
        } catch (Exception e) {
            throw new BaseException(ResponseMessage.EMAIL_OR_PASSWORD_INVALID);
        }
    }

    @Override
    public DtoRefreshToken refreshToken(DtoRefreshTokenIU dtoRefreshTokenIU) {
        RefreshToken refreshToken = refreshTokenService.findRefreshTokenOrThrow(dtoRefreshTokenIU.getRefreshToken());
        if (!refreshTokenService.validateRefreshToken(refreshToken.getExpiredDate())) {
            throw new BaseException(ResponseMessage.TOKEN_EXPIRED);
        }
        Users user = refreshToken.getUser();
        String accessToken = jwtService.generateToken(user);
        return new DtoRefreshToken(accessToken);
    }
}
