package com.murathnakts.service.impl;

import com.murathnakts.entity.User;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements IJwtService {

    private static final String SECRET_KEY = "6Zd3mcyAW7oVujbs56ifZ37LbQ0nTep/sd7lvg++UH0=";
    private static final String OTP_SECRET_KEY = "6Zd3mcyAW7oVujbs79ifZ56LbQ0nTep/sd7lvg++UH0=";

    @Override
    public Key getKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    @Override
    public Key getOtpKey() {
        byte[] bytes = Decoders.BASE64.decode(OTP_SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public Claims getOtpClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getOtpKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //TODO convert local date time
    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateTokenOtp(String email) {
        return Jwts.builder()
                .setSubject(email)
                .claim("type", "RESET_PASSWORD")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getOtpKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public <E> E exportToken(String token, Function<Claims, E> claimsFunction) {
        Claims claims = getClaims(token);
        return claimsFunction.apply(claims);
    }

    @Override
    public <E> E exportOtpToken(String token, Function<Claims, E> claimsFunction) {
        Claims claims = getOtpClaims(token);
        return claimsFunction.apply(claims);
    }

    @Override
    public Boolean isTokenValid(String token) {
        Date expireDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expireDate);
    }

    @Override
    public Boolean isOtpTokenValid(String token) {
        try {
            Claims claims = getOtpClaims(token);
            String type = (String) claims.get("type");
            if (!"RESET_PASSWORD".equals(type)) {
                return false;
            }
            return new Date().before(claims.getExpiration());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getEmailByToken(String token) {
        return exportToken(token, Claims::getSubject);
    }

    @Override
    public String getEmailByOtpToken(String token) {
        return exportOtpToken(token, Claims::getSubject);
    }

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User user) {
            return user.getId();
        }
        throw new BaseException(ResponseMessage.UNAUTHORIZED_USER);
    }

    @Override
    public String getCurrentUserEmailOtp() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails().toString();
    }
}
