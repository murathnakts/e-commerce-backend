package com.murathnakts.service;

import com.murathnakts.entity.Otp;

public interface IOtpService {
    Otp findByEmail(String email);
    String generateOtp();
    Otp createOtp(String email, String code);
    void deleteOtp(String email);
    void sendOtp(String email);
    Boolean verifyOtp(String email, String code);
}
