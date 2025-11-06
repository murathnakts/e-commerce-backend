package com.murathnakts.controller;

import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;

public interface IAuthController {
    DtoUser getUserById(Long id);
    DtoUser register(DtoUserIU dtoUserIU);
}
