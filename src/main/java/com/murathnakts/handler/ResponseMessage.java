package com.murathnakts.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    SUCCESS(HttpStatus.OK, "İşlem Başarılı"),
    USER_CREATED(HttpStatus.CREATED, "Kullanıcı Başarıyla Oluşturuldu."),
    LOGIN_SUCCESS(HttpStatus.OK, "Giriş Başarılı"),


    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Kullanıcı Bulunamadı"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Bir Sorun Oluştu");

    private final HttpStatus httpStatus;
    private final String message;

    ResponseMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
