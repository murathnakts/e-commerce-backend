package com.murathnakts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DtoRefreshTokenIU {
    @NotBlank
    private String refreshToken;
}
