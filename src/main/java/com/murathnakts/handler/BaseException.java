package com.murathnakts.handler;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final ResponseMessage responseMessage;

    public BaseException(ResponseMessage responseMessage) {
        super(responseMessage.getMessage());
        this.responseMessage = responseMessage;
    }
}
