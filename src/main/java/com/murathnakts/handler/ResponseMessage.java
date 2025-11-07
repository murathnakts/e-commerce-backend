package com.murathnakts.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    //SUCCESS
    SUCCESS(HttpStatus.OK, "Başarılı."),

    LOGIN_SUCCESS(HttpStatus.OK, "Giriş başarılı."),
    TOKEN_REFRESHED(HttpStatus.OK, "Token yenilendi."),

    USER_CREATED(HttpStatus.CREATED, "Kullanıcı başarıyla kayıt edildi."),

    //FAILURE
    USERNAME_OR_PASSWORD_INVALID(HttpStatus.UNAUTHORIZED, "Kullanıcı Adı veya Şifre Yanlış."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token süresi dolmuş."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "Token geçersiz."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "Kullanıcı yetkisi yok."),

    USER_ALREADY_REGISTERED(HttpStatus.CONFLICT, "Kullanıcı zaten kayıtlı."),
    USER_ALREADY_ADDED(HttpStatus.CONFLICT, "Kullanıcı zaten ekli."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Kullanıcı Bulunamadı."),

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Bir Sorun Oluştu. Lütfen daha sonra tekrar deneyiniz.");

    private final HttpStatus httpStatus;
    private final String message;

    ResponseMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
