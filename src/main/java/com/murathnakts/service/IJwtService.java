package com.murathnakts.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.function.Function;

public interface IJwtService {
    Key getKey();
    Key getOtpKey();
    Claims getClaims(String token);
    Claims getOtpClaims(String token);
    String generateToken(UserDetails userDetails);
    String generateTokenOtp(String email);
    <E> E exportToken(String token, Function<Claims, E> claimsFunction);
    <E> E exportOtpToken(String token, Function<Claims, E> claimsFunction);
    Boolean isTokenValid(String token);
    Boolean isOtpTokenValid(String token);
    String getEmailByToken(String token);
    String getEmailByOtpToken(String token);
    Long getCurrentUserId();
    String getCurrentUserEmailOtp();
}
