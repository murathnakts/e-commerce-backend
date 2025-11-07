package com.murathnakts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DtoLoginIU {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
