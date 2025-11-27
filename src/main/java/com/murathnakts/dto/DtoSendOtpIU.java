package com.murathnakts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DtoSendOtpIU {
    @NotBlank
    @Email
    private String email;
}
