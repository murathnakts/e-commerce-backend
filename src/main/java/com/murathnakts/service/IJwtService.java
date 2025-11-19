package com.murathnakts.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.function.Function;

public interface IJwtService {
    Key getKey();
    Claims getClaims(String token);
    String generateToken(UserDetails userDetails);
    <E> E exportToken(String token, Function<Claims, E> claimsFunction);
    boolean isTokenValid(String token);
    String getEmailByToken(String token);
    Long getCurrentUserId();
}
