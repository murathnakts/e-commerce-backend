package com.murathnakts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DtoResetPasswordIU {
    @NotBlank
    private String newPassword;
}
