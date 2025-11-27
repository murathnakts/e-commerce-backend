package com.murathnakts.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    //SUCCESS
    SUCCESS(HttpStatus.OK, "Başarılı."),

    LOGIN_SUCCESS(HttpStatus.OK, "Giriş başarılı."),
    TOKEN_REFRESHED(HttpStatus.OK, "Token yenilendi."),
    PRODUCT_UPDATED(HttpStatus.OK, "Ürün başarıyla güncellendi."),
    PRODUCT_DELETED(HttpStatus.OK, "Ürün başarıyla silindi."),
    OTP_SEND(HttpStatus.OK, "Otp başarıyla gönderildi."),
    OTP_VERIFY(HttpStatus.OK, "Otp başarıyla doğrulandı."),
    PASSWORD_RESET(HttpStatus.OK, "Şifre başarıyla sıfırlandı."),

    USER_CREATED(HttpStatus.CREATED, "Kullanıcı başarıyla kayıt edildi."),
    PRODUCT_ADDED(HttpStatus.CREATED, "Ürün başarıyla eklendi."),

    //FAILURE
    EMAIL_OR_PASSWORD_INVALID(HttpStatus.UNAUTHORIZED, "E-mail veya Şifre Yanlış."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token süresi dolmuş."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "Token geçersiz."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "Kullanıcı yetkisi yok."),
    OTP_EXPIRED(HttpStatus.UNAUTHORIZED, "Otp süresi dolmuş. Lütfen yeni bir otp isteyin."),
    OTP_NOT_VERIFIED(HttpStatus.UNAUTHORIZED, "Otp doğrulanamadı."),

    USER_ALREADY_REGISTERED(HttpStatus.CONFLICT, "Kullanıcı zaten kayıtlı."),
    USER_ALREADY_ADDED(HttpStatus.CONFLICT, "Kullanıcı zaten ekli."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Kullanıcı Bulunamadı."),
    OTP_NOT_FOUND(HttpStatus.NOT_FOUND, "Otp bulunamadı."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Ürün Bulunamadı."),

    OTP_MAX_TRY_EXCEEDED(HttpStatus.FORBIDDEN, "Otp deneme hakkınız doldu. Lütfen yeni bir otp isteyin."),

    MAIL_NOT_SEND(HttpStatus.INTERNAL_SERVER_ERROR, "Mail gönderimi başarısız oldu."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Bir Sorun Oluştu. Lütfen daha sonra tekrar deneyiniz.");

    private final HttpStatus httpStatus;
    private final String message;

    ResponseMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
