package com.murathnakts.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<?>> handleBaseException(BaseException ex){
        ResponseMessage responseMessage = ex.getResponseMessage();
        return ResponseEntity
                .status(responseMessage.getHttpStatus())
                .body(ApiResponse.failure(responseMessage.name(), ex.getMessage()));
    }

    //TODO add Validation Exception Handler
    //TODO add accessDenied Exception Handler

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex){
        ResponseMessage responseMessage = ResponseMessage.INTERNAL_ERROR;
        return ResponseEntity
                .status(responseMessage.getHttpStatus())
                .body(ApiResponse.failure(responseMessage.name(), ex.getMessage()));
    }
}
