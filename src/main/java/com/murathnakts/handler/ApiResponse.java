package com.murathnakts.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String status;
    private String error;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data, ResponseMessage message) {
        return new ApiResponse<>("true", null, message.getMessage(), data);
    }

    public static <T> ApiResponse<T> failure(String error, String message) {
        return new ApiResponse<>("false", error, message, null );
    }
}
