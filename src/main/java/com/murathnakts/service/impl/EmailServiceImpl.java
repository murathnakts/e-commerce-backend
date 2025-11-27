package com.murathnakts.service.impl;

import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender mailSender,
                            SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    @Override
    public void send(String email, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            Context context = new Context();
            context.setVariable("otpCode", otp);
            String htmlContent = templateEngine.process("otp-email", context);
            helper.setTo(email);
            helper.setSubject("Şifre Sıfırlama Doğrulama Kodu");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new BaseException(ResponseMessage.MAIL_NOT_SEND);
        }
    }
}
