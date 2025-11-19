package com.murathnakts.dto;

import com.murathnakts.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser extends DtoBase{
    private String email;
    private RoleType role;
}
