package com.murathnakts.service.impl;

import com.murathnakts.entity.Otp;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.repository.OtpRepository;
import com.murathnakts.service.IEmailService;
import com.murathnakts.service.IOtpService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements IOtpService {

    private final IEmailService emailService;
    private final OtpRepository otpRepository;
    private final PasswordEncoder otpEncoder;

    public OtpServiceImpl(IEmailService emailService,
                          OtpRepository otpRepository,
                          PasswordEncoder otpEncoder) {
        this.emailService = emailService;
        this.otpRepository = otpRepository;
        this.otpEncoder = otpEncoder;
    }

    @Override
    public Otp findByEmail(String email) {
        return otpRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ResponseMessage.OTP_NOT_FOUND));
    }

    @Override
    public String generateOtp() {
        return String.valueOf((100000 + new SecureRandom().nextInt(900000)));
    }

    @Override
    public Otp createOtp(String email, String code) {
        Otp otp = new Otp();
        otp.setEmail(email);
        otp.setOtp(otpEncoder.encode(code));
        otp.setExpireDate(LocalDateTime.now().plusMinutes(3));
        otp.setTryCount(0);
        return otp;
    }

    @Override
    public void deleteOtp(String email) {
        otpRepository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public void sendOtp(String email) {
        deleteOtp(email);
        String otp = generateOtp();
        otpRepository.save(createOtp(email,otp));
        emailService.send(email, otp);
    }

    @Override
    @Transactional(noRollbackFor = BaseException.class)
    public Boolean verifyOtp(String email, String code) {
        Otp otp = findByEmail(email);

        if (otp.getExpireDate().isBefore(LocalDateTime.now())) {
            deleteOtp(email);
            throw new BaseException(ResponseMessage.OTP_EXPIRED);
        }

        if (otp.getTryCount() >= 3) {
            deleteOtp(email);
            throw new BaseException(ResponseMessage.OTP_MAX_TRY_EXCEEDED);
        }

        if (!otpEncoder.matches(code, otp.getOtp())) {
            otp.setTryCount(otp.getTryCount() + 1);
            otpRepository.save(otp);
            throw new BaseException(ResponseMessage.OTP_NOT_VERIFIED);
        }

        deleteOtp(email);
        return true;
    }
}
