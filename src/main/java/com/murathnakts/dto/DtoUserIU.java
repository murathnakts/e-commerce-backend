package com.murathnakts.dto;

import com.murathnakts.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUserIU {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private RoleType role;
}
