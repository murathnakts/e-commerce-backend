package com.murathnakts.service.impl;

import com.murathnakts.dto.*;
import com.murathnakts.entity.RefreshToken;
import com.murathnakts.entity.Users;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.jwt.JwtService;
import com.murathnakts.service.IAuthService;
import com.murathnakts.service.IRefreshTokenService;
import com.murathnakts.service.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    private final IUserService userService;
    private final IRefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(IUserService userService,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService,
                           IRefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public DtoUser register(DtoUserIU dtoUserIU) {
        if (userService.isUsernameTaken(dtoUserIU.getUsername())) {
            throw new BaseException(ResponseMessage.USER_ALREADY_REGISTERED);
        }
        return userService.createUser(dtoUserIU);
    }

    @Override
    public DtoLogin login(DtoLoginIU request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(authenticationToken);
            Users user = userService.findByUsername(request.getUsername());
            String accessToken = jwtService.generateToken(user);
            RefreshToken savedRefreshToken = refreshTokenService.createRefreshToken(user);
            return new DtoLogin(user.getId(), accessToken, savedRefreshToken.getRefreshToken(), savedRefreshToken.getExpiredDate());
        } catch (Exception e) {
            throw new BaseException(ResponseMessage.USERNAME_OR_PASSWORD_INVALID);
        }
    }

    @Override
    public DtoRefreshToken refreshToken(DtoRefreshTokenIU request) {
        RefreshToken refreshToken = refreshTokenService.findRefreshTokenOrThrow(request.getRefreshToken());
        if (!refreshTokenService.validateRefreshToken(refreshToken.getExpiredDate())) {
            throw new BaseException(ResponseMessage.TOKEN_EXPIRED);
        }
        Users user = refreshToken.getUser();
        String accessToken = jwtService.generateToken(user);
        return new DtoRefreshToken(accessToken);
    }
}
