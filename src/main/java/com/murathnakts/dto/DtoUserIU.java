package com.murathnakts.dto;

import com.murathnakts.enums.RoleType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DtoUserIU {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private RoleType role;
}
