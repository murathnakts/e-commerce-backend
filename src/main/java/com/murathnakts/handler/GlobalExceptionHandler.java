package com.murathnakts.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<?>> handleBaseException(BaseException ex){
        ResponseMessage responseMessage = ex.getResponseMessage();
        return ResponseEntity
                .status(responseMessage.getHttpStatus())
                .body(ApiResponse.failure(responseMessage.name(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex){
        ResponseMessage responseMessage = ResponseMessage.VALIDATION_ERROR;

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(responseMessage.getHttpStatus())
                .body(ApiResponse.failure(responseMessage.name(), errorMessage));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(){
        ResponseMessage responseMessage = ResponseMessage.ACCESS_DENIED;

        return ResponseEntity
                .status(responseMessage.getHttpStatus())
                .body(ApiResponse.failure(responseMessage.name(), responseMessage.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex){
        ResponseMessage responseMessage = ResponseMessage.INTERNAL_ERROR;
        return ResponseEntity
                .status(responseMessage.getHttpStatus())
                .body(ApiResponse.failure(responseMessage.name(), ex.getMessage()));
    }
}
